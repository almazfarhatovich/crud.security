package peaksoft.restcrudlms.apies;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * @author almazfarhatovich
 */
@RestController
@RequestMapping("/api/test")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class TestController {

    @GetMapping("/hello")
    public String helloAdmin() {
        return "T am almaz, i am admin";
    }
}
