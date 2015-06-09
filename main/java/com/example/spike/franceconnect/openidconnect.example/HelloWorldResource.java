package com.example.spike.franceconnect.openidconnect.example;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.*;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;


@Path("")
public class HelloWorldResource {
    private static final String CLIENT_ID_DEFAULT = "";
    /**
     * Configure this:
     * CLIENT_ID
     * CLIENT_SECRET
     * CALLBACK_ADRESS
     *
     * Usually, this is provided by FranceConnect
     */
    public static final String CLIENT_ID = CLIENT_ID_DEFAULT;
    public static final String CLIENT_SECRET = CLIENT_ID_DEFAULT;
    private final String CALLBACK_ADDRESS = "http://localhost:8080/callback";
    private final String FRANCECONNECT_BASE_PATH = "https://fcp.integ01.dev-franceconnect.fr/api/v1/";

    @GET
    @Produces({MediaType.TEXT_HTML})
    public String getLoginHref() {

        return "<html><a href=\"login\">Login</a></html>";
    }

    @GET
    @Produces({MediaType.TEXT_HTML})
    @Path("login")
    public String login(){
        return getLoginAddress();
    }

    protected String getLoginAddress() {
        return "<html><head><meta http-equiv=\"refresh\" content=\"0; url=" +
                new URLBuilder(FRANCECONNECT_BASE_PATH + "authorize")
                        .parameter("response_type","code")
                        .parameter("client_id", CLIENT_ID)
                        .parameter("redirect_uri",CALLBACK_ADDRESS )
                        .parameter("scope","openid%20profile")
                .parameter("state","nclikn1m2lkm1231kj23n12l31l2jk312").parameter("nonce","123123123231112313\" />").addRest("");
    }

    @GET
    @Path("callback")
    public String helloWorld(@QueryParam("code") String code, @QueryParam("state") String state) throws JSONException {

        if(isNotConfigured()) {
            throw new IllegalArgumentException("Cannot continue without configuring. Sorry");
        }

        final ClientConfig configuration = new ClientConfig();
        configuration.property("com.sun.jersey.api.json.POJOMappingFeature", "true");
        Client client = ClientBuilder.newClient(configuration);

        // Get Token
        final WebTarget target1 = client.target(FRANCECONNECT_BASE_PATH);
        WebTarget target = target1.path("token");

        Form form = new Form();
        form.param("grant_type", "authorization_code");
        form.param("redirect_uri", CALLBACK_ADDRESS);
        form.param("client_id",CLIENT_ID);
        form.param("client_secret",CLIENT_SECRET);
        form.param("code", code);

        final Entity<Form> entity = Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE);
        final Invocation.Builder request = target.request(MediaType.APPLICATION_JSON_TYPE);
        String result = request.post(entity, String.class);
        System.out.println(result);

        JSONObject jsonObject = new JSONObject(result);
        String accessToken = jsonObject.getString("access_token");
        String tokenType = jsonObject.getString("token_type");
        String expiresIn = jsonObject.getString("expires_in");
        String idToken = jsonObject.getString("id_token");
        MyToken token = new MyToken(accessToken, tokenType, expiresIn, idToken);

        final Invocation.Builder personalDataRequest = target1.path("userinfo").queryParam("schema", "openid").request().header("Authorization", token.tokenType + " "+token.accessToken);
        String personalData = personalDataRequest.get(String.class);
        System.out.println(personalData);

        return "Received code and state : " + code+", " + state;
    }

    private boolean isNotConfigured() {
        return CLIENT_ID_DEFAULT.equals(CLIENT_ID) || CLIENT_ID_DEFAULT.equals(CLIENT_ID);
    }

}
