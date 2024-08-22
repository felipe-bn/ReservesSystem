import axios from "axios";

const API_URL = 'http://localhost:8080/auth';

class AuthenticationService {
    static async login(user){
        try{
            const response = await axios.post(`${API_URL}/login`, user, {
                headers: {
                    'Content-Type': 'application/json'
                }
            });
            return response.data;
        }
        catch(err){
            throw err;
        }
    }
    
}

export default AuthenticationService;