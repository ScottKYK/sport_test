const { defineConfig } = require('@vue/cli-service')
module.exports = {
  devServer: {
    proxy: {
      '/api': {                //   /api是习惯性的写法，可以随意改
        target: 'http://localhost:9000', //接口域名
        changeOrigin: true,             //是否跨域
        ws: true,                       //是否代理 websockets
        secure: true,                   //是否https接口
        pathRewrite: {                  //路径重置
          
        }
      }
    }
  }
};
