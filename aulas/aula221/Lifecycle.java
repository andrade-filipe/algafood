package com.esr.algafood.aulas.aula221;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class Lifecycle {
    @Component
    public class WithComponentAnnotation{
        @PostConstruct
        public void init(){

        }

        @PreDestroy
        public void destroy(){

        }
    }

    @Configuration
    public class WithConfigurationAnnotation{

        @Bean(initMethod = "init", destroyMethod = "destroy")
        public Lifecycle lifecycle(){
            return new Lifecycle();
        }
    }

    public void init(){

    }

    public void destroy(){

    }
}
