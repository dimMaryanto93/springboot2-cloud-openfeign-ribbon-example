## Spring Cloud Discovery Service

For first-time you need install the dependency using command:

```bash
mvn clean package install
```

Services: 

| Service Names  | Command                                          | Ports bind  |
|---             |---                                               |---          |
| api-gateway    | `mvn clean -pl netflix-zuul spring-boot:run`     | 8765        |
| eureka-server  | `mvn clean -pl eureka-server spring-boot:run`    | 8761        | 

## Maintainers

- Dimas Maryanto <software.dimas_m@icloud.com>

if you want to contribute please email me.
