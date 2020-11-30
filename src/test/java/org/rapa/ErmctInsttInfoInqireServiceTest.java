/**
 * 
 */
package org.rapa;

import static org.junit.Assert.*;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.UriUtils;

/**
 * @author suhk
 *
 */
public class ErmctInsttInfoInqireServiceTest {
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void testGetParmacyListInfoInqire() {
		String url = "http://apis.data.go.kr/B552657/ErmctInsttInfoInqireService/getParmacyListInfoInqire";
		String serviceKey = "aeTzzOFSwrqvBDKTkhKD0rGzwc%2BSDaHdsjgjwYMtBGtVY%2FEPXx5EHpabdqgZg5Kes2b%2FCBvZlQqx1wZNqp14Xg%3D%3D";
		String Q0 = "서울특별시";
		String Q1 = "강남구";
		String ORD = "ADDR";
		String pageNo = "1";
		String numOfRows = "100";
		
		XStreamMarshaller marshaller = new XStreamMarshaller();		
		List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
	    converters.add(new FormHttpMessageConverter());
	    converters.add(new StringHttpMessageConverter());
	    converters.add(new MarshallingHttpMessageConverter(marshaller));
	    
	    DefaultUriBuilderFactory defaultUriBuilderFactory = new DefaultUriBuilderFactory();
        defaultUriBuilderFactory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.NONE);
	    RestTemplate restTemplate = new RestTemplate();
	    restTemplate.setUriTemplateHandler(defaultUriBuilderFactory);
	    restTemplate.setMessageConverters(converters);
		
	    HttpHeaders headers = new HttpHeaders();
	    Charset utf8 = Charset.forName("UTF-8");
        MediaType mediaType = new MediaType("application", "xml", utf8);
        headers.setContentType(mediaType);
        
        String Q0enc = UriUtils.encode(Q0, "UTF-8");
        String Q1enc = UriUtils.encode(Q1, "UTF-8");
        System.out.println("[URI] Q0enc: " + Q0enc);
        System.out.println("[URI] Q1enc: " + Q1enc);
	    
	    UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
	            .queryParam("serviceKey", serviceKey)
	            .queryParam("Q0", Q0enc)
	            .queryParam("Q1", Q1enc)
	            .queryParam("ORD", ORD)
	            .queryParam("pageNo", pageNo)
	            .queryParam("numOfRows", numOfRows);
	    String uriString = builder.build(false).toString();
	    System.out.println("[URI] uriString: " + uriString);
	    
	    HttpEntity<String> entity = new HttpEntity<String>(headers);
	    
		HttpEntity<String> response = restTemplate.exchange(
	            uriString, 
	            HttpMethod.GET, 
	            entity, 
	            String.class);
		
		System.out.println("[Respose] Body: " + response.toString());
	}
	
	@Test
	public void testgetParmacyBassInfoInqire() throws Exception {
		
	}

}
