package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/math")
public class MathController {

    @Autowired
    private  MathService mathService;

    @GetMapping("/pi")
    public String getPi() {
        return mathService.getPi();
    }

    @GetMapping("/calculate")
    public int getCalculation(QueryParamInfo queryParamInfo)
    {
        int result = 0;
        try
        {
         if (queryParamInfo == null || (queryParamInfo != null && queryParamInfo.getOperation() == null))
                return result;

                result = mathService.getDynamicCalucationResult(queryParamInfo);

        } catch (Exception ex) {
            System.out.println("Exception in getCalculation: " + ex.getMessage());

        }
     return result;
    }
    @PostMapping("/sum")
    public int postSum(@RequestParam MultiValueMap<String, String> querystring)
    {
        int result = 0;
    try {

            int index = 0;
            if (querystring != null )
            {
                result = mathService.getCalulatedSum(querystring);
            }
        } catch (Exception ex) {
            System.out.println("Exception in postSum: " + ex.getMessage());

        }
        return result;

    }

    @PostMapping("/volume/{length}/{width}/{height}")
    public String postVolume(@PathVariable int length,@PathVariable int width,@PathVariable int height)
    {
        return mathService.getCalculatedVolume(length,width,height);
    }
}

