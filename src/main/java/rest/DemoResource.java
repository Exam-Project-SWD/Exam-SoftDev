package rest;

import com.google.gson.*;
import entities.Customer;
import facades.UserFacade;
import entities.User;

import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import utils.EMF_Creator;


@Path("info")
public class DemoResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final UserFacade uf = UserFacade.getUserFacade(EMF);

    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getInfoForAll() {
        return "{\"msg\":\"Hello anonymous\"}";
    }

    //Just to verify if the database is setup
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("all")
    public String allUsers() {

        EntityManager em = EMF.createEntityManager();
        try {
            TypedQuery<User> query = em.createQuery ("select u from User u", User.class);
            List<User> users = query.getResultList();
            return "[" + users.size() + "]";
        } finally {
            em.close();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("user")
    @RolesAllowed("user")
    public String getFromUser() {
        String thisuser = securityContext.getUserPrincipal().getName();
        return "{\"msg\": \"Hello to User: " + thisuser + "\"}";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("admin")
    @RolesAllowed("admin")
    public String getFromAdmin() {
        String thisuser = securityContext.getUserPrincipal().getName();
        return "{\"msg\": \"Hello to (admin) User: " + thisuser + "\"}";
    }


    @GET
    @Path("users")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public Response seeAllUsers() {
        return Response.ok()
                .entity(GSON.toJson(uf.seeAllUsers()))
                .build();
    }


    @POST
    @Path("users/changeUserRole")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public Response changeUserRole(String jsonString) {
        String userName;
        String role;
        try {
            JsonObject json = JsonParser.parseString(jsonString).getAsJsonObject();
            userName = json.get("userName").getAsString();
            role = json.get("role").getAsString();

            return Response.ok()
                    .entity(GSON.toJson(uf.changeUserRole(userName, role)))
                    .build();

        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    private JsonObject getNoUrl(String sentUrl) {
        try {
            URL url = new URL(sentUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            System.out.println("conn");
            System.out.println(conn);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("User-Agent", "server");
            conn.setRequestProperty("Accept", "application/json;charset=UTF-8");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP Error code : "
                        + conn.getResponseCode());
            }
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);
            String output = br.readLine();
            JsonObject convertedObject = new Gson().fromJson(output, JsonObject.class);
            conn.disconnect();
            return convertedObject;

        } catch (Exception e) {
            System.out.println("Exception in NetClientGet:- " + e);
            JsonObject error = new Gson().fromJson(new Gson().toJson(e), JsonObject.class);
            return error;
        }
    }
}
