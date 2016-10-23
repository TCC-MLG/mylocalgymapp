package gym.com.br.mylocalgym.requesters;

import android.os.StrictMode;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import gym.com.br.mylocalgym.Parameters.SolicitarCheckinParameter;

/**
 * Created by Matheus on 23/10/2016.
 */

public class CheckinRequester {

    private void ativarPolicy(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public Integer solicitarCheckin(Integer clienteId, Integer academiaId) {

        this.ativarPolicy();

        final String url = "http://192.168.43.48:8080/mylocalgym/resources/checkin/solicitar";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        SolicitarCheckinParameter parameter = new SolicitarCheckinParameter();
        parameter.setClienteId(clienteId);
        parameter.setAcademiaId(academiaId);

        try {

            Integer saldo = restTemplate.postForObject(url, parameter, Integer.class);

            return saldo;
        } catch (Exception e) {


        }
        return null;
    }

}