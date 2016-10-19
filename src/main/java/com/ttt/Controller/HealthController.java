package com.ttt.Controller;

/**
 * Created by agubba on 10/16/16.
 */

import com.ttt.Service.TTTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class HealthController {

    @Autowired
    private TTTService tttService;

    @RequestMapping(method=RequestMethod.GET)
    public @ResponseBody String sayHello(@RequestParam(value="name", required=false, defaultValue="Stranger") String name) {
        return tttService.getAllChannels();
    }

}