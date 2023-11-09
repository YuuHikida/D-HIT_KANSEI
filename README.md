# Business-reporting-system


## ローカル開発

* ローカルDBへの接続設定  
以下設定ファイルのDB接続情報を書き換えてください。
src/main/resources/application-development.properties

```
spring.datasource.url=jdbc:mysql://localhost:XXXX/database_name
spring.datasource.username=hoge
spring.datasource.password=hogehoge
```

* ローカルでアプリを実行する場合の注意点  
コマンドライン引数で以下のオプションを付与してください  
--spring.profiles.active=development  
※ 実行環境ごとに設定ファイルを切り替えるため
