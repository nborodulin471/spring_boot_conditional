package com.borodulin.spring_boot_conditional.infrastructure.profile.impl;

import com.borodulin.spring_boot_conditional.infrastructure.profile.SystemProfile;

public class ProductionProfile implements SystemProfile {
    @Override
    public String getProfile() {
        return "Current profile is production";
    }
}
