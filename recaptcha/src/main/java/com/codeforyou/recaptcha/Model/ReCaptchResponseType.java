package com.codeforyou.recaptcha.Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReCaptchResponseType { 
	 
        private boolean success;
        private String challenge_ts;
        private String hostname;
        
        public boolean isSuccess() {
            return success;
        }
    
}
