interface UpdateUserPlayload {
    name: string;
}

class UserApi {
    private baseUrl: string;
    private readonly API_BASE_PATH = '/api/v1/users';

    constructor(baseUrl: string) {
        this.baseUrl = baseUrl;
    }

    async getUser(userId: number) {
        const response = await fetch(`${this.baseUrl}${this.API_BASE_PATH}/${userId}`,
            {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json'
                }
            });
            return response.json();
    }

    async patchUser(userId: number, payload: UpdateUserPlayload) {
        const response = await fetch(`${this.baseUrl}${this.API_BASE_PATH}/${userId}`, {
            method: 'PATCH',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(payload)
        });
        return response.json();;
    }
}

export default UserApi;