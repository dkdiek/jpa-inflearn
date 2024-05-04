package jpabook.jpashop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * jpabook.jpashop HelloController
 *
 * @author : K
 */
@Controller
public class HelloController {

  @GetMapping("hello")
  public String hello(Model model){
    model.addAttribute("data", "Hello");
    return "hello";
}
}
