package folaSmile.spring02.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {

        model.addAttribute("data", "Data from hello controller");
        return "hello";

    }

    //@RequestParam 의 옵션 required = false         -> 8080/hello-mvc -> 페이지가 로드됨
    //                               true(default) -> 8080/hello-mvc -> 로드되지 않음 (Whitelabel Error Page)
    //                                             -> 8080/hello-mvc?name=fola -> 페이지가 로드됨
    //                                             -> 8080/hello-mvc?name      -> 페이지가 로드됨
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name", required = true) String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }


    @GetMapping("hello-string")
    @ResponseBody // html <body></body> 부분에 직접 넣어주겠다는 어노테이션
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }


    @GetMapping("hello-api")
    @ResponseBody
    public HelloClass helloApi(@RequestParam("name") String name) {
        HelloClass hello = new HelloClass();
        hello.setName(name);
        return hello;


    }

    static class HelloClass {

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
