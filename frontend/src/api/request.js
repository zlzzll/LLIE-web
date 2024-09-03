import axios from "axios";
const api = axios.create(
	{ 
		baseURL: "/api", //这里配置的是后端服务提供的接口
		timeout: 15000 
	}
);
export default api;
