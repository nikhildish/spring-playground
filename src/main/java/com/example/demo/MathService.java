package com.example.demo;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;

@Service
public class MathService {

    public MathService() {
    }


    public int getCalulatedSum(MultiValueMap<String, String> querystring)
    {
        int sum = 0;
        try {

            int index = 0;
            if (querystring != null )
            {
                for (Object value : querystring.get("n")) {
                    sum += Integer.parseInt(value.toString());
                    index++;
                }
            }
        } catch (Exception ex) {
            System.out.println("Exception in getCalulatedSum: " + ex.getMessage());

        }
        return sum;

    }

    public int getDynamicCalucationResult(QueryParamInfo queryParamInfo) {int result = 0;
        try {

            if (queryParamInfo == null || (queryParamInfo != null && queryParamInfo.getOperation() == null))
                return result;

            switch (queryParamInfo.getOperation()) {
                case "add":
                    result = Integer.parseInt(queryParamInfo.getX()) + Integer.parseInt((queryParamInfo.getY()));
                    break;
                case "substract":
                    result = Integer.parseInt(queryParamInfo.getX()) - Integer.parseInt((queryParamInfo.getY()));
                    break;
                case "multiply":
                    result = Integer.parseInt(queryParamInfo.getX()) * Integer.parseInt((queryParamInfo.getY()));
                    break;
                case "divide":
                    result = Integer.parseInt(queryParamInfo.getX()) / Integer.parseInt((queryParamInfo.getY()));
                    break;
            }


        } catch (Exception ex) {
            System.out.println("Exception in getCalculation: " + ex.getMessage());

        }

        return result;

    }

    public String getCalculatedVolume(int length, int width, int height)
    {

        return String.format("The volume of a %d x %d x %d rectangle is %d",length,width,height,length*width*height);
    }

    public String getCalculatedArea(String type, int radius, int width,int height)
    {
        String result = "";
            try {

                switch (type) {
                    case "circle":
                        if (radius == 0)
                            result = "Invalid" ;
                        else
                        result =  String.format("Area of a circle with a radius of %d is %f",radius, Double.parseDouble(getPi())*radius*radius);
                        break;

                    case "rectangle":
                        if (width == 0 || height == 0 )
                            result = "Invalid" ;
                        else
                        result =  String.format("Area of a %d x %d rectangle is %d",width, height,width*height);
                        break;
                    default:
                        result  = "Invalid";
                }


            } catch (Exception ex) {
                System.out.println("Exception in getCalculatedArea: " + ex.getMessage());

            }

           return result;
        }




    public String getPi() {
        return "3.141592653589793";
    }
}
