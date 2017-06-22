/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monami.autotrek.exception;

/**
 *
 * @author praveens
 */
class ResponseMsg {
     private String message;
 
    public ResponseMsg(String msg){
        this.message = msg;
    }
     
    public String getMessage() {
        return message;
    }
 
    public void setMessage(String message) {
        this.message = message;
    }

}
