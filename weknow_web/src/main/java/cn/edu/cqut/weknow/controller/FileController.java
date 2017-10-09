package cn.edu.cqut.weknow.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/files")
public class FileController
{
	/**
	 * 文件上传功能
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/uploadPic")
	public void uploadPic(MultipartFile file, HttpServletRequest request) throws IOException
	{
		try
		{
			String path = request.getServletContext().getRealPath("/") + File.separator + "uploads" + File.separator
					+ "images" + File.separator;
			String fileName = file.getOriginalFilename();
			File dir = new File(path, fileName);
			System.out.println("上传图片文件保存路径：" + dir.getPath());
			if (!dir.exists())
				dir.mkdirs();

			file.transferTo(dir);// MultipartFile自带的解析方法
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	@RequestMapping("/uploadAudio")
	public void uploadAudio(MultipartFile file, HttpServletRequest request) throws IOException
	{
		try
		{
			String path = request.getServletContext().getRealPath("/") + File.separator + "uploads" + File.separator
					+ "audios" + File.separator;
			String fileName = file.getOriginalFilename();
			File dir = new File(path, fileName);
			System.out.println("上传音频文件保存路径：" + dir.getPath());
			if (!dir.exists())
				dir.mkdirs();
			
			file.transferTo(dir);// MultipartFile自带的解析方法
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	@RequestMapping("/uploadVideo")
	public void uploadVideo(MultipartFile file, HttpServletRequest request) throws IOException
	{
		try
		{
			String path = request.getServletContext().getRealPath("/") + File.separator + "uploads" + File.separator
					+ "videos" + File.separator;
			String fileName = file.getOriginalFilename();
			File dir = new File(path, fileName);
			System.out.println("上传视频文件保存路径：" + dir.getPath());
			if (!dir.exists())
				dir.mkdirs();
			
			file.transferTo(dir);// MultipartFile自带的解析方法
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

	/**
	 * 文件下载功能
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/downloadPicFile")
	public void downloadPicFile(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String downloadFileName = request.getParameter("fileName");
		if (StringUtils.isEmpty(downloadFileName))
			return;
		// 需要下载的文件
		String fileFullName = request.getSession().getServletContext().getRealPath("uploads") + File.separator
				+ "images" + File.separator + downloadFileName;
//		System.out.println("收到图片文件下载请求，下载文件全路径：" + fileFullName);
		File file = new File(fileFullName);
		if (!file.exists())
			return;
		// 获取输入流
		InputStream bis = new BufferedInputStream(new FileInputStream(file));
		// 假如以中文名下载的话
		String fileName = downloadFileName;
		// 转码，免得文件名中文乱码
		fileName = URLEncoder.encode(fileName, "UTF-8");
		// 设置文件下载头
		response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
		// 1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
		response.setContentType("multipart/form-data");
		BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
		int len = -1;
		byte[] data = new byte[1024];
		while ((len = bis.read(data)) != -1)
		{
			out.write(data, 0, len);
			out.flush();
		}
		out.close();
		bis.close();
	}
	/**
	 * 文件下载功能
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/downloadAudioFile")
	public void downloadAudioFile(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String downloadFileName = request.getParameter("fileName");
		if (StringUtils.isEmpty(downloadFileName))
			return;
		// 需要下载的文件
		String fileFullName = request.getSession().getServletContext().getRealPath("uploads") + File.separator
				+ "audios" + File.separator + downloadFileName;
		System.out.println("收到音频文件下载请求，下载文件全路径：" + fileFullName);
		File file = new File(fileFullName);
		if (!file.exists())
			return;
		// 获取输入流
		InputStream bis = new BufferedInputStream(new FileInputStream(file));
		// 假如以中文名下载的话
		String fileName = downloadFileName;
		// 转码，免得文件名中文乱码
		fileName = URLEncoder.encode(fileName, "UTF-8");
		// 设置文件下载头
		response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
		// 1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
		response.setContentType("multipart/form-data");
		BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
		int len = -1;
		byte[] data = new byte[1024];
		while ((len = bis.read(data)) != -1)
		{
			out.write(data, 0, len);
			out.flush();
		}
		out.close();
		bis.close();
	}
	
	/**
	 * 文件下载功能
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/downloadVideoFile")
	public void downloadVideoFile(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String downloadFileName = request.getParameter("fileName");
		if (StringUtils.isEmpty(downloadFileName))
			return;
		// 需要下载的文件
		String fileFullName = request.getSession().getServletContext().getRealPath("uploads") + File.separator
				+ "videos" + File.separator + downloadFileName;
		System.out.println("收到视频文件下载请求，下载文件全路径：" + fileFullName);
		File file = new File(fileFullName);
		if (!file.exists())
			return;
		// 获取输入流
		InputStream bis = new BufferedInputStream(new FileInputStream(file));
		// 假如以中文名下载的话
		String fileName = downloadFileName;
		// 转码，免得文件名中文乱码
		fileName = URLEncoder.encode(fileName, "UTF-8");
		// 设置文件下载头
		response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
		// 1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
		response.setContentType("multipart/form-data");
		BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
		int len = -1;
		byte[] data = new byte[1024];
		while ((len = bis.read(data)) != -1)
		{
			out.write(data, 0, len);
			out.flush();
		}
		out.close();
		bis.close();
	}	
}
