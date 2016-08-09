
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by saifularifin on 6/29/2016.
 */
public class ReadAPITests {

    private static String readUrl(String urlString) throws Exception {
        BufferedReader reader = null;
        try {
            System.getProperties().put("http.proxyHost", "10.13.13.215");
            System.getProperties().put("http.proxyPort", "8808");
            System.getProperties().put("http.proxyUser", "aloysiusrizkanp");
            System.getProperties().put("http.proxyPassword", "Haroldaia1");

            URL url = new URL(urlString);

            HttpURLConnection httpcon = (HttpURLConnection) url.openConnection();
            httpcon.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");

            httpcon.getInputStream();

            reader = new BufferedReader(new InputStreamReader(httpcon.getInputStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);

            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }
    }

    public static void main(String[] args) {
//        try {
//            ReadAPITests.readUrl("http://www.google.com/finance/info?q=NASDAQ:GOOG");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        DefaultHttpClient client = new DefaultHttpClient();
//        HttpHost proxy = new HttpHost("10.13.13.215", 8808, "http");
//        HttpHost target = new HttpHost("http://www.google.com/finance/info?q=NASDAQ:GOOG", 443, "http");
//        client.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
//
//        Credentials credentials = new UsernamePasswordCredentials("aloysiusrizkanp", "Haroldaia1");
//        client.getCredentialsProvider().setCredentials(AuthScope.ANY,
//                credentials);
//
//        HttpProtocolParams.setUserAgent(client.getParams(), "Mozilla/5.0 (Windows NT 5.1; rv:19.0) Gecko/20100101 Firefox/19.0");
//        HttpGet get = new HttpGet("/");
//
//        try {
//
//            CloseableHttpResponse response = client.execute(target, get);
//
//            BufferedReader br = new BufferedReader(
//                    new InputStreamReader((response.getEntity().getContent())));
//
//            String output;
//            System.out.println("Output from Server .... \n");
//            while ((output = br.readLine()) != null) {
//                System.out.println(output);
//            }
//
//            client.close();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }



        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet getRequest = new HttpGet("http://www.google.com/finance/info?q=NASDAQ:GOOG");
            getRequest.addHeader("accept", "application/json");

            httpClient.getCredentialsProvider().setCredentials(
                    new AuthScope("10.13.13.215", 8808),
                    new UsernamePasswordCredentials("aloysiusrizkanp", "Haroldaia1"));

            HttpHost proxy = new HttpHost("10.13.13.215", 8808, "http");
            httpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);

            Authenticator.setDefault(new ProxyAuthenticator("user", "password"));
            System.setProperty("http.proxyHost", "10.13.13.215");
            System.setProperty("http.proxyPort", "8808");
            System.setProperty("http.proxyUser", "aloysiusrizkanp");
            System.setProperty("http.proxyPassword", "Haroldaia1");

//            Credentials credentials = new UsernamePasswordCredentials("aloysiusrizkanp", "Haroldaia1");
//            httpClient.getCredentialsProvider().setCredentials(AuthScope.ANY, credentials);

            HttpResponse response = httpClient.execute(getRequest);

            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatusLine().getStatusCode());
            }

            BufferedReader br = new BufferedReader(
                    new InputStreamReader((response.getEntity().getContent())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }

            httpClient.getConnectionManager().shutdown();

        } catch (ClientProtocolException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();
        }

    }



}
