package org.spring.dev.company.config;

import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.core.Komoran;
import lombok.RequiredArgsConstructor;
import org.spring.dev.company.repository.member.MemberRepository;
import org.spring.dev.company.repository.weather.WeatherRepository;
<<<<<<< Updated upstream
=======
import org.spring.dev.openApi.movie.repository.MovieRepository;
>>>>>>> Stashed changes
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class KomoranConfig {

    private String USER_DIC = "user.dic";
    private final MemberRepository memberRepository;
    private final WeatherRepository weatherRepository;
<<<<<<< Updated upstream
=======
    private final MovieRepository movieRepository;
>>>>>>> Stashed changes

    @Bean
    Komoran komoran(){
        userDic();
        Komoran komoran=new Komoran(DEFAULT_MODEL.LIGHT);
        komoran.setUserDic(USER_DIC);

        return komoran;
    }

    private void userDic() {

        Set<String> keys = new HashSet<>();

        try {
            File file=new File(USER_DIC);
            if(file.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String data = null;
                while ((data = br.readLine()) != null) {
                    if (data.startsWith("#"))
                        continue;
                    String[] str = data.split("\\t");
                    keys.add(str[0]);
                }
                br.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        memberRepository.findAll().forEach(e->{
            keys.add(e.getName());
            keys.add(e.getCompanyName());
        });
        weatherRepository.findAll().forEach(e->{
            keys.add(e.getName());
        });

<<<<<<< Updated upstream
        keys.add("ì„œìš¸");
        keys.add("ê´‘ì£¼");
        keys.add("ë¶€ì‚°");
        keys.add("ì¶˜ì²œ");
        keys.add("ë‚ ì”¨");
=======

        keys.add("¼­¿ï");
        keys.add("ºÎ»ê");
        keys.add("±¤ÁÖ");
        keys.add("ÃáÃµ");
        keys.add("³¯¾¾");

>>>>>>> Stashed changes


        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(USER_DIC));
            keys.forEach(key -> {
                try {
                    bw.write(key + "\tNNP\n");
                    System.out.println(key);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            });

            bw.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }
}



