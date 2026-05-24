
package tumeloquickchat;

public class Part1 {

   



        private String userId;
        private String userSecret;
        private String localPhone;
        private String savedUserId;
        private String savedSecret;
        private String givenName;
        private String familyName;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserSecret() {
            return userSecret;
        }

        public void setUserSecret(String userSecret) {
            this.userSecret = userSecret;
        }

        public String getLocalPhone() {
            return localPhone;
        }

        public void setLocalPhone(String localPhone) {
            this.localPhone = localPhone;
        }

        public String getSavedUserId() {
            return savedUserId;
        }

        public void setSavedUserId(String savedUserId) {
            this.savedUserId = savedUserId;
        }

        public String getSavedSecret() {
            return savedSecret;
        }

        public void setSavedSecret(String savedSecret) {
            this.savedSecret = savedSecret;
        }

        public String getGivenName() {
            return givenName;
        }

        public void setGivenName(String givenName) {
            this.givenName = givenName;
        }

        public String getFamilyName() {
            return familyName;
        }

        public void setFamilyName(String familyName) {
            this.familyName = familyName;
        }

        public boolean validateUserId(String inputUserId) {
            if (inputUserId == null) {
                return false;
            } else {
                if (inputUserId.contains("_")) {
                    if (inputUserId.length() <= 5) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        }

        public boolean checkPasswordStrength(String inputSecret) {
            if (inputSecret == null) {
                return false;
            }

            
            if (inputSecret.length() < 10 || inputSecret.contains(" ")) {
                return false;
            }

       
            if (!inputSecret.matches(".*[A-Z].*")) {
                return false;
            }
            if (!inputSecret.matches(".*[a-z].*")) {
                return false;
            }
            if (!inputSecret.matches(".*[0-9].*")) {
                return false;
            }
            if (!inputSecret.matches(".*[^A-Za-z0-9].*")) {
                return false;
            }

           
            String currentUserId = getUserId();
            if (currentUserId != null && !currentUserId.isEmpty()) {
                if (inputSecret.toLowerCase().contains(currentUserId.toLowerCase())) {
                    return false;
                }
            }

   
            if (inputSecret.matches(".*(.)(\\1){2}.*")) {
                return false;
            }

            return true;
        }

        public boolean validateSouthAfricanPhone(String inputPhone) {
            String regex = "^\\+27\\d{9}$";
    return inputPhone.matches(regex);
        }

        public String createAccount(String inputUserId, String inputSecret) {
            setUserId(inputUserId);
            setUserSecret(inputSecret);

            if (!validateUserId(getUserId())) {
                return "The username is incorrectly formatted.";
            } else {
                if (!checkPasswordStrength(getUserSecret())) {
                    return "The password does not meet the complexity requirements";
                } else {
                    setSavedUserId(getUserId());
                    setSavedSecret(getUserSecret());
                    return "The two above conditions have been met, and the user has been registered successfully.";
                }
            }
        }

        public boolean verifyCredentials(String inputUserId, String inputSecret) {
            String storedId = getSavedUserId();
            String storedSecret = getSavedSecret();

            if (storedId == null || storedSecret == null) {
                return false;
            } else {
                if (storedId.equals(inputUserId)) {
                    if (storedSecret.equals(inputSecret)) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        }

        public String getLoginMessage(String inputUserId, String inputSecret) {
            if (verifyCredentials(inputUserId, inputSecret)) {
                String g = getGivenName();
                String f = getFamilyName();
                if (g == null) {
                    g = "";
                }
                if (f == null) {
                    f = "";
                }
                return "Welcome " + g + ", " + f + " it is great to see you again.";
            } else {
                return "Login failed! Please check your username and password and try again.";
            }
        }
    }

