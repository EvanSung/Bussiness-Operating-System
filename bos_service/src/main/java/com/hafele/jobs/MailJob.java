package com.hafele.jobs;

import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;

import com.hafele.bos.dao.IWorkbillDao;
import com.hafele.bos.domain.Workbill;
import com.sun.mail.util.MailSSLSocketFactory;

/**
 * <p>Title: MailJob</p>  
 * <p>Description: 发送邮件的作业</p>  
 * @author Dragon.Wen
 * @date Aug 29, 2018
 */
public class MailJob {

	@Autowired
	private IWorkbillDao workbillDao;
	
	public void execute() {
		System.out.println("要发邮件了。。。" + new Date());
		try {
			//查询工单类型为新单的所有工单
			List<Workbill> list = workbillDao.findAll();
			if(list != null && list.size() > 0){
				// 跟smtp服务器建立一个连接
				Properties p = new Properties();
				// 设置邮件服务器主机名
				p.setProperty("mail.host", "smtp.qq.com");// 指定邮件服务器，默认端口 25
				// 发送服务器需要身份验证
				p.setProperty("mail.smtp.auth", "true");// 要采用指定用户名密码的方式去认证
				// 发送邮件协议名称
				p.setProperty("mail.transport.protocol", "smtp");

				// 开启SSL加密，否则会失败
				MailSSLSocketFactory sf = null;
				try {
					sf = new MailSSLSocketFactory();
				} catch (GeneralSecurityException e1) {
					e1.printStackTrace();
				}
				sf.setTrustAllHosts(true);
				p.put("mail.smtp.ssl.enable", "true");
				p.put("mail.smtp.ssl.socketFactory", sf);

				// 开启debug调试，以便在控制台查看
				// session.setDebug(true);也可以这样设置
				// p.setProperty("mail.debug", "true");

				// 创建session
				Session session = Session.getDefaultInstance(p, new Authenticator() {
					@Override
					protected PasswordAuthentication getPasswordAuthentication() {
						// 用户名可以用QQ账号也可以用邮箱的别名
						PasswordAuthentication pa = new PasswordAuthentication(
								"920310436", "ynzdkkbkqgpbbfdi");
						// 后面的字符是授权码，用qq密码不行！！
						return pa;
					}
				});

				session.setDebug(true);// 设置打开调试状态
				
				for(Workbill workbill : list){
					// 声明一个Message对象(代表一封邮件),从session中创建
					MimeMessage msg = new MimeMessage(session);
					// 邮件信息封装
					// 1发件人
					msg.setFrom(new InternetAddress("920310436@qq.com"));
					// 2收件人
					msg.setRecipient(RecipientType.TO, new InternetAddress("18475536452@163.com"));
					// 3邮件内容:主题、内容
					msg.setSubject("系统邮件：新单通知");

					// StringBuilder是线程不安全的,但是速度快，这里因为只会有这个线程来访问，所以可以用这个
					//StringBuilder sbd = new StringBuilder();

					msg.setContent("类型:"+workbill.getType()+"；状态："+workbill.getPickstate()+"；时间："+workbill.getBuildtime()+"；备注："+workbill.getRemark(), "text/html;charset=utf-8");// 发html格式的文本
					
					// 发送动作
					Transport.send(msg);
					
					System.out.println("发送邮件成功。");
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
