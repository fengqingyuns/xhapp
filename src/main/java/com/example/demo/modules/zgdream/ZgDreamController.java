/*package com.example.demo.modules.zgdream;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.demo.modules.elsticearch.dao.ZhouGongDreamRepository;
import com.example.demo.modules.util.AJAXResult;
import com.example.demo.modules.xiaohua.entity.Dream;
import com.example.demo.modules.xiaohua.service.ZhouGongSleepDreamService;
import com.example.demo.modules.zgdream.entity.ExcelDream;
import com.example.demo.util.ExcelUtil;
*/
/*@Controller
public class ZgDreamController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ZgDreamController.class);
	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;
	@Autowired
	ZhouGongSleepDreamService zhouGongSleepDreamService;
	@Autowired
	ZhouGongDreamRepository zhouGongDreamRepository;
	 String[][] headers = {{"梦境","30"},{"描述","100"},{"时间","50"}};    
	 private static String[] fieldValues = {"title","des", "time"};
	@RequestMapping("/toDream")
	public String toDream() {
		return "zgdream/dream";
	}
	
	
	@RequestMapping("/getDream")
	@ResponseBody
	public AJAXResult getDream(String dreamInfo,@PageableDefault(size = 20) Pageable pageable) {
		//elasticsearchTemplate.deleteIndex("zgdream");
		AJAXResult result = new AJAXResult();AJAXResult reresult = new AJAXResult();
		List<Dream> list = new ArrayList<Dream>();
		List<Dream> dreamForList = null;
		SearchQuery searchQuery = null;
		String json = null;
	//	pageable = PageRequest.of(1, 2);
		//elasticsearchTemplate.deleteIndex("");
		searchQuery = new NativeSearchQueryBuilder().withQuery(new MatchQueryBuilder("title", dreamInfo))
				.withPageable(pageable).build();
		dreamForList = elasticsearchTemplate.queryForList(searchQuery, Dream.class);
		LOGGER.info("dreamForList :{}", dreamForList);
		if(null == dreamForList ||dreamForList.size() == 0) {
			result = zhouGongSleepDreamService.getSleepDream(dreamInfo);
		
		if (null != result) {
			try {

				list = (ArrayList<Dream>) result.getData();
				list.forEach(dream -> {
					zhouGongDreamRepository.save(dream);
				});

				LOGGER.info("获取dream数据:{}", list.toArray().toString());
			} catch (Exception e) {
				LOGGER.error("获取dream数据失败:{}", e);
			}finally {
				searchQuery = new NativeSearchQueryBuilder().withQuery(new MatchQueryBuilder("title", dreamInfo))
						.withPageable(pageable).build();
				dreamForList = elasticsearchTemplate.queryForList(searchQuery, Dream.class);
				if(null != dreamForList) {
					reresult.setData(dreamForList);
					reresult.setSuccess(true);
				}
			}
		}else {
			reresult.setMsg("不存在");
			reresult.setSuccess(false);
		}
		}else {
			reresult.setData(dreamForList);
			reresult.setSuccess(true);
		}
		
		return reresult;
	}
	@RequestMapping(value = "/expdream")
	public void expdream(HttpServletResponse response, HttpServletRequest request,String dreamInfo,@PageableDefault(size = 20) Pageable pageable) throws IOException{
	//	List<Dream> list = new ArrayList<Dream>();
		
		List<Dream> dreamForList = null;List<ExcelDream> dreamForExcel = new ArrayList<ExcelDream>();
		SearchQuery searchQuery = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		searchQuery = new NativeSearchQueryBuilder().withQuery(new MatchQueryBuilder("title", dreamInfo))
				.withPageable(pageable).build();
		dreamForList = elasticsearchTemplate.queryForList(searchQuery, Dream.class);
		String expFileName = dreamInfo;
		
        OutputStream out = null;
        response.reset();// 清空输出流
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition",
                "attachment;filename=" + new String((expFileName + ".xls").getBytes(), "iso-8859-1"));
        out = response.getOutputStream();// 取得输出流
        dreamForList.forEach(dream->{
        	dreamForExcel.add(new ExcelDream(dream.getTitle(),dream.getDes(), dream.getTime()));
        });
        try {
            new ExcelUtil<ExcelDream>(expFileName, headers, fieldValues, dreamForExcel, out)
                    .ExportExcel();
        } catch (Exception e) {
            LOGGER.error("生成文件失败!", e);
        } finally {
            out.close();
        }
	}
}
*/