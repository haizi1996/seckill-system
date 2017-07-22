package seckill.web;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import seckill.dto.Exposer;
import seckill.dto.SeckillExecution;
import seckill.dto.SeckillResult;
import seckill.enums.SeckillStateEnum;
import seckill.exception.RepeatKillException;
import seckill.exception.SeckillCloseException;
import seckill.pojo.Seckill;
import seckill.service.SeckillService;
import seckill.util.JsonUtil;

@Controller
public class SeckillController {
	
	@Autowired
	private SeckillService seckillService;
	
	@RequestMapping(value="/seckill/list",method=RequestMethod.GET)
	public String list(Model model) {
		List<Seckill> list = seckillService.getSeckillList();
		model.addAttribute("list", list);
		return "list";
	}
	
	@RequestMapping(value="/seckill/{seckillId}/detail",method=RequestMethod.GET)
	public String showdetail(@PathVariable("seckillId")Long seckillId,Model model) {
		
		if(seckillId == null) {
			return "redirect:/seckill/list";
		}
		Seckill seckill = seckillService.selectSeckillDetailById(seckillId);
		if(seckill == null) {
			return "forward:/seckill/list";
		}
		model.addAttribute("seckill", seckill);
		return "detail";
	}
	
	@RequestMapping(value="/seckill/time/now",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
	@ResponseBody
	public String getCurrentTime(){
		Date date = new Date();
		return JsonUtil.toJSON(new SeckillResult<Long>(true, date.getTime()));
		
	}
	
	@RequestMapping(value="/seckill/{seckillId}/exposer" ,method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
	@ResponseBody
	public String exposer(@PathVariable("seckillId") Long seckillId) {
		SeckillResult<Exposer> result;
		Exposer exposer = seckillService.exportSeckillUrl(seckillId);
		if(exposer != null)
			result = new SeckillResult<Exposer>(true,exposer);
		else {
			result = new SeckillResult<Exposer>(false, "error message !");
		}
		return JsonUtil.toJSON(result);
	}
	
	
	@RequestMapping(value="/seckill/{seckillId}/{md5}/execution",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
	@ResponseBody
	public SeckillResult<SeckillExecution> execute(@PathVariable("seckillId") Long seckillId,
			@PathVariable("md5") String md5, @CookieValue(value = "killPhone", required = false) Long phone) {
		if (phone == null) {
			return new SeckillResult<SeckillExecution>(false, "未注册");
		}
		try {
//			SeckillExecution execution = seckillService.executeSeckill(seckillId, phone, md5);
			SeckillExecution execution = seckillService.executeSeckillProcedure(seckillId, phone, md5);
			return new SeckillResult<SeckillExecution>(true,execution);
		} catch (RepeatKillException e) {
			SeckillExecution execution = new SeckillExecution(seckillId, SeckillStateEnum.REPEAT_KILL);
			return new SeckillResult<SeckillExecution>(true, execution);
		} catch (SeckillCloseException e) {
			SeckillExecution execution = new SeckillExecution(seckillId, SeckillStateEnum.END);
			return new SeckillResult<SeckillExecution>(true, execution);
		} catch (Exception e) {
			SeckillExecution execution = new SeckillExecution(seckillId, SeckillStateEnum.INNER_ERROR);
			return new SeckillResult<SeckillExecution>(true, execution);
		}
	}

}
