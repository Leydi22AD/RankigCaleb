package upeu.edu.pe.RCaleb.configs;

import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
@Configuration
@OpenAPIDefinition(
        info=@Info(
                title = "RCaleb CRUD",version = "1.0.0",description = "this is a CRUD from management rcaleb"
        )
)
public class OpenApiConfig {

}
