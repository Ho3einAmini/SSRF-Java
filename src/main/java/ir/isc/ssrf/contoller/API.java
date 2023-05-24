package ir.isc.ssrf.contoller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.stream.Collectors;


@RestController("API")
public class API {

    @GetMapping("/")
    private ModelAndView index()
    {
        return new ModelAndView("index");
    }

    @GetMapping("/check")
    private ModelAndView blind()
    {
        return new ModelAndView("blind");
    }


    @GetMapping("/unblock")
    private String run(HttpServletRequest request)
    {
        try {
            String location = request.getParameter("url");
            //this code vuln to file:///d:/temp/ssrf.txt and localhost request
            URL url = new URL( location);
            URLConnection connection = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            return reader.lines().collect(Collectors.joining());
        } catch (Exception e) {
            return "Not Found";
        }
    }
    @GetMapping("/blind")
    private String blind(HttpServletRequest request)
    {
        try {
            String location = request.getParameter("url");
            //this code vuln to file:///d:/temp/ssrf.txt and localhost request
            URL url = new URL( location);
            URLConnection connection = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String result = reader.lines().collect(Collectors.joining());
            return "This is NOT blocked";

        } catch (Exception e) {
            return "This site is blocked";
        }
    }

}
