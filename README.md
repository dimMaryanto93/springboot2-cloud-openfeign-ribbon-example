## Spring Cloud Discovery Service

For first-time you need install the dependency using command:

```bash
mvn clean package install
```

Services: 

| Service Names     | Command                                          | Ports bind         | Required                          |
|---                |---                                               |---                 | ---                               |
| eureka-server     | `mvn clean -pl eureka-server spring-boot:run`    | 8761               | always                            |
| api-gateway       | `mvn clean -pl netflix-zuul spring-boot:run`     | 8765               | always                            |
| wilayah-service   | `mvn clean -pl wilayah-service spring-boot:run`  | [8011..8020]       | Required by `nasabah-service`     |
| nasabah-service   | `mvn clean -pl nasabah-service spring-boot:run`  | [8021..8030]       | Optional                          |

## Maintainers

- Dimas Maryanto <software.dimas_m@icloud.com>

if you want to contribute please email me.
