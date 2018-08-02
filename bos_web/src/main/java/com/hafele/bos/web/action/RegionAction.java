package com.hafele.bos.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hafele.bos.domain.Region;
import com.hafele.bos.service.IRegionService;
import com.hafele.bos.utils.PinYin4jUtils;
import com.hafele.bos.web.action.base.BaseAction;
/**
 * <p>Title: RegionAction</p>  
 * <p>Description: 区域设置Action</p>  
 * @author Dragon.Wen
 * @date Aug 1, 2018
 */
@Controller
@Scope("prototype")
public class RegionAction extends BaseAction<Region> {

	/**
	 *  serialVersionUID
	 */
	private static final long serialVersionUID = -5957172546226009029L;
	
	//属性驱动，接收上传的文件
	private File regionFile;
	
	private String q;

	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}

	public void setRegionFile(File regionFile) {
		this.regionFile = regionFile;
	}

	@Autowired
	private IRegionService regionService;

	/**
	 * <p>Title: importXls</p>  
	 * <p>Description: 文件上传方法</p>  
	 * @return
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	@SuppressWarnings("resource")
	public String importXls() throws FileNotFoundException, IOException {
		List<Region> regionList = new ArrayList<Region>();
		//使用POI解析Excel文件
		HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(regionFile));
		//根据名称获得指定Sheet对象
		HSSFSheet hssfSheet = workbook.getSheet("Sheet1");
		for (Row row : hssfSheet) {
			int rowNum = row.getRowNum();
			if(rowNum == 0){
				continue;
			}
			String id = row.getCell(0).getStringCellValue();
			String province = row.getCell(1).getStringCellValue();
			String city = row.getCell(2).getStringCellValue();
			String district = row.getCell(3).getStringCellValue();
			String postcode = row.getCell(4).getStringCellValue();
			//包装一个区域对象
			Region region = new Region(id, province, city, district, postcode, null, null, null);
			
			province = province.substring(0, province.length() - 1);
			city = city.substring(0, city.length() - 1);
			district = district.substring(0, district.length() - 1);
			String info = province + city + district;
			String[] headByString = PinYin4jUtils.getHeadByString(info);
			String shortcode = StringUtils.join(headByString);
			//城市编码---->>shijiazhuang
			String citycode = PinYin4jUtils.hanziToPinyin(city, "");
			
			region.setShortcode(shortcode);
			region.setCitycode(citycode);
			regionList.add(region);
		}
		//批量保存
		regionService.saveBatch(regionList);
		return NONE;
	}
	
	/**
	 * <p>Title: pageQuery</p>  
	 * <p>Description: 分页查询</p>  
	 * @return
	 * @throws IOException 
	 */
	public String pageQuery() throws IOException {		
		regionService.pageQuery(pageBean);
		this.java2Json(pageBean, new String[] {"currentPage","pageSize","detachedCriteria","subareas"});
		return NONE;
	}
	
	/**
	 * <p>Title: listajax</p>  
	 * <p>Description: 查询所有区域，返回json数据</p>  
	 * @return
	 */
	public String listajax() {
		List<Region> list = null;
		if(StringUtils.isNotBlank(q)) {
			list = regionService.findListByQ(q);
		}else {
			list = regionService.findAll();
		}
		this.java2Json(list, new String[] {"subareas"});
		return NONE;
	}
}
