package org.exoplatform.samples;
import juzu.Path;
import juzu.View;
import juzu.Response;
import juzu.template.Template;
import javax.inject.Inject;
import java.io.IOException;
public class Controller {
	@Inject
	@Path("index.gtmpl")
	Template index;
	@View
	public Response.Content index() throws IOException {
		return index.ok();
	}
}