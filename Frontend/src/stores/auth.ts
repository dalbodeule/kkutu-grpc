import { defineStore } from 'pinia'
import nuxt from 'nuxt'

export const useAuthStore = defineStore('auth', () => {
    const authenticated = ref(false)
    const loading = ref(false)
    const userInfo = ref({
        id: '',
        vendor: '',
        image: '',
        nickname: '',
    })

    const token = useCookie('token')
    const config = useRuntimeConfig()
    const AUTH_SERVER = config.public.AUTH_SERVER

    if(token.value) {
        authenticated.value = true
    }

    const getUserMeta = async() => {
        loading.value = true;
        const response: {id: string, vendor: string, profileImageUrl: string, nickname: string, token: string}  = await $fetch(`${AUTH_SERVER}/userinfo`, {
            method: 'GET',
            credentials: 'include'
        })
        if(response) {
            userInfo.value.id = response.id
            userInfo.value.vendor = response.vendor
            userInfo.value.image = response.profileImageUrl
            userInfo.value.nickname = response.nickname
            token.value = response.token
        } else {
            token.value = null
            // refreshToken.value = null
            authenticated.value = false

            // await refresh()
            await getUserMeta()
        }
    }


    const logout = async () => {
        loading.value = true
        // @ts-ignore
        /*const response = await $fetch(`${AUTH_SERVER}/logout/`, {
        })*/
        loading.value = false
        authenticated.value = false
        token.value = null
        userInfo.value = {
            id: '',
            vendor: '',
            nickname: '',
            image: ''
        }
        loading.value = false
    };

    /* const refresh = async () => {
        loading.value = true
        // @ts-ignore
        const response: {access: string, refresh: string} = await $fetch(`${AUTH_SERVER}/users/login/refresh/`,{
            method: 'POST',
            body: {
                refresh: refreshToken.value
            },
            headers: {
                'Authorization': `Bearer ${token.value}`
            }
        })

        if(response) {
            token.value = response.access
            refreshToken.value = response.refresh
        } else {
            token.value = null
            refreshToken.value = null
            authenticated.value = false
        }

        loading.value = false
    } */

    return {authenticated, loading, logout, token, userInfo, getUserMeta }
})