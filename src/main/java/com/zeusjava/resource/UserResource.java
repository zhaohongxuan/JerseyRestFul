package com.zeusjava.resource;
import com.sun.jersey.api.NotFoundException;
import com.zeusjava.cache.UserCache;
import com.zeusjava.entity.User;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.xml.bind.JAXBElement;
import java.io.IOException;
import java.net.URI;

/**
 * Created By IntelliJ IDEA.
 * User: LittleXuan
 * Date: 2015/11/18.
 * Time: 17:08
 * Desc: User对外开放的RestFul服务
 */
@Path("/users")
public class UserResource {
    @Context
    UriInfo uriInfo;
    /**
     * 根据id查找User
     * @param id
     * @return
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public User getUser(@PathParam("id") String id) {

        User user = UserCache.getUserCache().get(id);
        if(user==null){
            throw new NotFoundException("No such User.");
        }
        return user;
    }

    /**
     * 增加用户
     * @param userId
     * @param userName
     * @param userAge
     * @param servletResponse
     * @throws IOException
     */
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void newUser(
            @FormParam("userId") String userId,
            @FormParam("userName") String userName,
            @FormParam("userAge") int userAge,
            @Context HttpServletResponse servletResponse
    ) throws IOException {
        User user = new User(userId,userName,userAge);
        UserCache.getUserCache().put(userId,user);
        URI uri = uriInfo.getAbsolutePathBuilder().path(userId).build();
        Response.created(uri).build();
    }

    /**
     * 更新用户
     * @param jaxbContact
     * @return
     */
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_XML)
    public Response putUser(JAXBElement<User> jaxbContact,@PathParam("id") String id) {
        User user = jaxbContact.getValue();
        Response res;
        if(UserCache.getUserCache().containsKey(id)) {
            res = Response.noContent().build();
        } else {
            res = Response.created(uriInfo.getAbsolutePath()).build();
        }
        UserCache.getUserCache().put(user.getUserId(), user);
        return res;
    }

    @DELETE
    @Path("/{id}")
    public void deleteContact(@PathParam("id") String id) {
        User user = UserCache.getUserCache().remove(id);
        if(user==null)
            throw new NotFoundException("No such User.");
    }




}
