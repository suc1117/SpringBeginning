package cs.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    // MVC (Model View Controller) - Basic structure
    // viewResolver return Transformed HTML to web browser
    // /hello-mvc?name=suc
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    // Just pass the data
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello "+name;
    }

    // REST API
    @GetMapping("hello-api")
    @ResponseBody   // Returned object transform to JSON (not use viewResolver)
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    private static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    //sample
    @GetMapping("hello")    // Get: "/hello" */
    public String hello(Model model) {  // spring add a Model here
        model.addAttribute("data", "hello!");   // add model(data:hello!)
        return "hello";     // resources:templates/hello.html
    }
}
