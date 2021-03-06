package test

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("/")
class TestResource {

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  @Path("/hello")
  fun hello() = "Pawel is here"
}