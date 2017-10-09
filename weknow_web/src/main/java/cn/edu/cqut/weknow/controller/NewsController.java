package cn.edu.cqut.weknow.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.edu.cqut.weknow.customer.po.NavigatorAnchor;
import cn.edu.cqut.weknow.customer.po.Result;
import cn.edu.cqut.weknow.customer.po.WeKnowException;
import cn.edu.cqut.weknow.po.News;
import cn.edu.cqut.weknow.service.INewsService;
import cn.edu.cqut.weknow.utils.pages.PagedResult;

@Controller
@RequestMapping("/news")
public class NewsController {
	
	@Autowired
	private INewsService newsService;
	@RequestMapping("/main")
	public void main(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
	{
		System.out.println("===============NewsController::main()============");
		String pageNoString = httpServletRequest.getParameter("pageNo");
		int pageNo = 1;
		if(pageNoString != null)
		{
			try
			{
				pageNo = Integer.parseInt(pageNoString);
			}
			catch (Exception e) {
				pageNo = 1;
			}
		}
		PagedResult<News> pageResult = newsService.list(null, pageNo);
		httpServletRequest.setAttribute("pageResult", pageResult);
		List<NavigatorAnchor> anchors = new ArrayList<NavigatorAnchor>();
		NavigatorAnchor navigatorAnchor = new NavigatorAnchor(httpServletRequest.getContextPath() + "/news/main.action","新闻管理设置");
		anchors.add(navigatorAnchor);
		httpServletRequest.setAttribute("anchors", anchors);
		httpServletRequest.setAttribute("pageTitle", "新闻管理");
		httpServletRequest.getRequestDispatcher("/WEB-INF/jsps/news/main.jsp")
				.forward(httpServletRequest, httpServletResponse);		
	}
	
	@RequestMapping("/add")
	public void add(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
			throws Exception
	{
		List<NavigatorAnchor> anchors = new ArrayList<NavigatorAnchor>();
		NavigatorAnchor navigatorAnchor = new NavigatorAnchor(httpServletRequest.getContextPath() + "/news/main.action","新闻管理设置");
		anchors.add(navigatorAnchor);
		navigatorAnchor = new NavigatorAnchor(httpServletRequest.getContextPath() + "/news/add.action","新增新闻");
		anchors.add(navigatorAnchor);
		httpServletRequest.setAttribute("anchors", anchors);
		httpServletRequest.setAttribute("pageTitle", "新增新闻");	
		httpServletRequest.getRequestDispatcher("/WEB-INF/jsps/news/edit.jsp")
				.forward(httpServletRequest, httpServletResponse);
	}

	@RequestMapping("/edit")
	public void edit(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
			throws Exception
	{
		try
		{
			String id = httpServletRequest.getParameter("id");
			News news = newsService.find(Integer.parseInt(id));
			httpServletRequest.setAttribute("news", news);
			List<NavigatorAnchor> anchors = new ArrayList<NavigatorAnchor>();
			NavigatorAnchor navigatorAnchor = new NavigatorAnchor(httpServletRequest.getContextPath() + "/news/main.action","新闻管理设置");
			anchors.add(navigatorAnchor);
			navigatorAnchor = new NavigatorAnchor(httpServletRequest.getContextPath() + "/news/edit.action","修改新闻");
			anchors.add(navigatorAnchor);
			httpServletRequest.setAttribute("anchors", anchors);
			httpServletRequest.setAttribute("pageTitle", "修改新闻");				
			httpServletRequest.getRequestDispatcher("/WEB-INF/jsps/news/edit.jsp")
					.forward(httpServletRequest, httpServletResponse);
		}
		catch (Exception e)
		{
			WeKnowException weKnowException = new WeKnowException("获取新闻信息：" + e.getMessage());
			throw weKnowException;
		}
	}
	
	
	//添加工具方法
	public String writeFile(String path, MultipartFile news_file) 
	throws Exception{
		try {
			
			//原始文件名称
			String File_name = null;
			if(news_file!=null){
				File_name =  news_file.getOriginalFilename();
			}
			if(news_file!=null&&File_name!=null&&File_name.length()>0){
				//新文件名称
				String newFileName = UUID.randomUUID().toString()+File_name.substring(File_name.lastIndexOf("."));
				//上传文件
				File newFile = new java.io.File(path+newFileName);
				if(!newFile.exists()){
					newFile.mkdirs();
				}
				//将内存中的文件写入磁盘
				news_file.transferTo(newFile);
				return newFileName;
			}
		} catch (Exception e) {
			return null;
			
		}
		return null;
	}
	
	
	@RequestMapping("/editSubmit")
	@ResponseBody
	public Result editSubmit(HttpServletRequest httpServletRequest, HttpSession session, News news, MultipartFile news_image, MultipartFile news_audio, MultipartFile news_video)
		throws Exception{
		
		Result result = new Result();
		try
		{
				//磁盘路径
				String url = session.getServletContext().getRealPath("/");
				//项目路径
				String url2 = httpServletRequest.getContextPath();
				//图片
				int flag = 0;
				String image_fileName = null;
				String image_path = url+"images/";
				
				image_fileName = writeFile(image_path, news_image);
				if(image_fileName==null){
					flag= 1;
				}
				if(flag==0){
					//设置属性
					String image_path2 = url2+"/images/"+image_fileName;
					news.setImageurl(image_path2);
				}
				//音频
				flag = 0;//复原
				String audio_path = url+"audio/";
				String audio_fileName = null;
				audio_fileName = writeFile(audio_path, news_audio);
				if(audio_fileName==null){
					flag= 1;
				}
				if(flag==0){
					//设置属性
					String audio_path2 = url2+"/audio/"+audio_fileName;
					news.setAudiourl(audio_path2);
				}
				//视频
				flag = 0;//复原
				String video_path = url+"video/";
				String video_fileName = null;
				video_fileName = writeFile(video_path, news_video);
				if(video_fileName==null){
					flag= 1;
				}
				if(flag==0){
					//设置属性
					String video_path2 = url2+"/video/"+video_fileName;
					news.setVideourl(video_path2);;
				}
				
				
			//表示新增
			if(news.getId()==null)
				newsService.add(news);
			//表示更新
			else
				newsService.update(news);
			
			result.setResult(Result.SUCCESS);
		}
		catch (Exception e)
		{
			result.setResult(Result.FAIL);
			result.setErrorMsg(e.getMessage());
		}
		return result;
	}
	
	//删除文件工具方法
	public void delete_file(String fileName, String url){
		if(fileName!=null&&fileName!=""){
			String path = "C:/Users/12070/git/weknowServer/weknow_web/src/main/webapp/"+url;
			File file = new File(path);
			if(file.exists()){
				file.delete();
			}
		}
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Result delete(HttpSession session, HttpServletRequest httpServletRequest)
			throws Exception
	{
		Result result = new Result();
		try
		{
			String id = httpServletRequest.getParameter("id");
			//找到该条记录
			News news = newsService.find(Integer.parseInt(id));
			String image = news.getImageurl();
			String audio = news.getAudiourl();
			String video = news.getVideourl();
			//删除图片文件
			delete_file(image,"images/"+image);
			//删除音频文件
			delete_file(audio,"audio/"+audio);
			//删除视频文件
			delete_file(video,"video/"+video);
			
			//从数据库中删除记录
			newsService.delete(Integer.parseInt(id));
			//返回结果
			result.setResult(Result.SUCCESS);
		}
		catch (Exception e)
		{
			result.setResult(Result.FAIL);
			result.setErrorMsg(e.getMessage());
		}
		return result;
	}	
}
