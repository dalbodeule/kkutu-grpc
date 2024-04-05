// https://nuxt.com/docs/api/configuration/nuxt-config
export default defineNuxtConfig({
  devtools: { enabled: true },
  app: {
    head: {
      charset: 'utf-8',
      viewport: 'width=device-width, initial-scale=1',
    }
  },
  modules: [
    '@pinia/nuxt'
  ],
  runtimeConfig: {
    public: {
      AUTH_SERVER: process.env.AUTH_SERVER || 'http://localhost:8080'
    }
  }
})
