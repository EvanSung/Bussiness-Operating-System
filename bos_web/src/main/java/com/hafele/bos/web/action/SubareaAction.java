package com.hafele.bos.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hafele.bos.domain.Region;
import com.hafele.bos.domain.Subarea;
import com.hafele.bos.service.ISubareaService;
import com.hafele.bos.utils.FileUtils;
import com.hafele.bos.web.action.base.BaseAction;

/**
 * <p>Title: SubareaAction</p>  
 * <p>Description: 管理分区Action</p>  
 * @author Dragon.Wen
 * @date Aug 2, 2018
 */
@Controller
@Scope("prototype")
public class SubareaAction extends BaseAction<Subarea> {

	/**
	 *  serialVersionUID
	 */
	private static final long serialVersionUID = -7251431507689259093L;
	
	//属性驱动，接收上传的文件
	private File subareaFile;
	
	//分区ids
	private String ids;
	
	//接收定区id
	private String decidedzoneId;

	public void setDecidedzoneId(String decidedzoneId) {
		this.decidedzoneId = decidedzoneId;
	}

	public void setSubareaFile(File subareaFile) {
		this.subareaFile = subareaFile;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
	
	@Autowired
	private ISubareaService subareaService;
	
	/**
	 * <p>Title: add</p>  
	 * <p>Description: 添加分区</p>  
	 * @return
	 */
	public String add() {
		subareaService.add(model);
		return LIST;
	}
	
	/**
	 * <p>Title: pageQuery</p>  
	 * <p>Description: 分区分页查询</p>  
	 * @return
	 */
	public String pageQuery() {
		DetachedCriteria dc = pageBean.getDetachedCriteria();
		
		//地址关键字
		String addressKey = model.getAddresskey();
		
		if(StringUtils.isNotBlank(addressKey)) {
			//添加一个过滤条件，根据地址关键字进行模糊查询
			dc.add(Restrictions.like("addresskey", "%"+addressKey+"%"));
		}
		
		Region region = model.getRegion();
		if(region != null) {
			//使用框架提供的别名方式实现多表关联查询
			dc.createAlias("region", "r");
			
			//省
			String province = region.getProvince();
			
			//市
			String city = region.getCity();
			
			//区
			String district = region.getDistrict();
			
			if(StringUtils.isNotBlank(province)) {
				//添加过滤条件，根据省进行模糊查询
				dc.add(Restrictions.like("r.province", "%"+province+"%"));
			}
			
			if(StringUtils.isNotBlank(city)) {
				//添加过滤条件，根据市进行模糊查询
				dc.add(Restrictions.like("r.city", "%"+city+"%"));
			}
			
			if(StringUtils.isNotBlank(district)) {
				//添加过滤条件，根据区进行模糊查询
				dc.add(Restrictions.like("r.district", "%"+district+"%"));
			}
		}
		
		subareaService.pageQuery(pageBean);
		this.java2Json(pageBean, new String[] {"currentPage","detachedCriteria","pageSize","decidedzone","subareas"});
		return NONE;
	}
	
	/**
	 * <p>Title: delete</p>  
	 * <p>Description: 删除分区</p>  
	 * @return
	 */
	public String delete() {
		subareaService.delete(ids);
		return LIST;
	}
	
	/**
	 * <p>Title: edit</p>  
	 * <p>Description: 修改分区</p>  
	 * @return
	 */
	public String edit() {
		//先查询数据库原始数据
		String id = model.getId();
		Subarea subarea = subareaService.findById(id);
		//根据页面提交的参数进行覆盖，参数已经封装到model对象
		subarea.setRegion(model.getRegion());
		subarea.setAddresskey(model.getAddresskey());
		subarea.setStartnum(model.getStartnum());
		subarea.setEndnum(model.getEndnum());
		subarea.setSingle(model.getSingle());
		subarea.setPosition(model.getPosition());
		
		subareaService.update(subarea);
		return LIST;
	}
	
	/**
	 * <p>Title: importXls</p>  
	 * <p>Description: 文件导入数据方法</p>  
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public String importXls() throws FileNotFoundException, IOException {
		List<Subarea> subareaList = new ArrayList<Subarea>();
		//使用POI解析Excel文件
		HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(subareaFile));
		//根据名称获得指定Sheet对象
		HSSFSheet hssfSheet = workbook.getSheet("Sheet1");
		for (Row row : hssfSheet) {
			int rowNum = row.getRowNum();
			if(rowNum == 0){
				continue;
			}
			String id = row.getCell(0).getStringCellValue();
			String addresskey = row.getCell(1).getStringCellValue();
			String startnum = row.getCell(2).getStringCellValue();
			String endnum = row.getCell(3).getStringCellValue();
			String single = row.getCell(4).getStringCellValue();
			String position = row.getCell(5).getStringCellValue();
			
			//包装一个分区对象
			Subarea subarea = new Subarea(id,null,null,addresskey,startnum,endnum,single,position);
			
			subareaList.add(subarea);
		}
		//批量保存
		subareaService.saveBatch(subareaList);
		return NONE;
	}
	
	/**
	 * <p>Title: ecportXls</p>  
	 * <p>Description: 导出分区数据</p>  
	 * @return
	 * @throws IOException 
	 */
	public String exportXls() throws IOException {
		//查询所有分区
		List<Subarea> list = subareaService.findAll();
		//使用POI将数据写入Excel文件
		//在内存中创建一个Excel文件
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
		//在Excel文件中创建一个sheet标签页
		HSSFSheet sheet = hssfWorkbook.createSheet("分区数据");
		//创建标题行
		HSSFRow titleRow = sheet.createRow(0);
		titleRow.createCell(0).setCellValue("分区编号");
//		titleRow.createCell(1).setCellValue("区域编码");
		titleRow.createCell(1).setCellValue("关键字");
		titleRow.createCell(2).setCellValue("起始号");
		titleRow.createCell(3).setCellValue("终止号");
		titleRow.createCell(4).setCellValue("单双号");
		titleRow.createCell(5).setCellValue("位置信息");
		
		//变量list集合，将分区数据写到Excel表格中
		for(Subarea subarea : list) {
			HSSFRow dataRow = sheet.createRow(sheet.getLastRowNum()+1);
			dataRow.createCell(0).setCellValue(subarea.getId());
//			dataRow.createCell(1).setCellValue(subarea.getDecidedzone().getId());
			dataRow.createCell(1).setCellValue(subarea.getAddresskey());
			dataRow.createCell(2).setCellValue(subarea.getStartnum());
			dataRow.createCell(3).setCellValue(subarea.getEndnum());
			dataRow.createCell(4).setCellValue(subarea.getSingle());
			dataRow.createCell(5).setCellValue(subarea.getPosition());
		}
		
		String filename = "分区数据.xls";
		String contentType = ServletActionContext.getServletContext().getMimeType(filename);
		ServletOutputStream out = ServletActionContext.getResponse().getOutputStream();
		ServletActionContext.getResponse().setContentType(contentType);
		
		//获取客户端浏览器类型
		String agent = ServletActionContext.getRequest().getHeader("User-Agent");
		filename = FileUtils.encodeDownloadFilename(filename, agent);
		ServletActionContext.getResponse().setHeader("content-disposition", "attachment;filename="+filename);
		hssfWorkbook.write(out);
		
		return NONE;
	}
	
	/**
	 * <p>Title: listajax</p>  
	 * <p>Description: 查询所有未分配到定区的分区</p>  
	 * @return
	 */
	public String listajax() {
		List<Subarea> list = subareaService.findListNotAssociation();
		this.java2Json(list, new String[] {"decidedzone","region"});
		return NONE;
	}
	
	/**
	 * <p>Title: findListByDecidedzoneId</p>  
	 * <p>Description: 根据定区id查询对应的分区</p>  
	 * @return
	 */
	public String findListByDecidedzoneId(){
		List<Subarea> list = subareaService.findListByDecidedzoneId(decidedzoneId);
		this.java2Json(list, new String[] {"decidedzone","subareas"});
		return NONE;
	}
}
