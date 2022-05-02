package com.borodulin.spring_boot_conditional.infrastructure.profile.config;

import com.borodulin.spring_boot_conditional.infrastructure.profile.SystemProfile;
import com.borodulin.spring_boot_conditional.infrastructure.profile.impl.DevProfile;
import com.borodulin.spring_boot_conditional.infrastructure.profile.impl.ProductionProfile;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigProfile {

    @Bean
    @ConditionalOnProperty(value="netology.profile.dev", havingValue="true")
    public SystemProfile devProfile() {
        return new DevProfile();
    }

    @Bean
    @ConditionalOnMissingBean
    public SystemProfile prodProfile() {
        return new ProductionProfile();
    }
}
