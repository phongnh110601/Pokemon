package com.ddona.pokemon.di_demo;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class Driver {

    @Inject
    public Driver() {
    }

    public String getName(){
        return "Phong dep try";
    }
}
