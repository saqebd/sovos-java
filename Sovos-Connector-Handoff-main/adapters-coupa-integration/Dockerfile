FROM sovosctoofcglobalcicdacr.azurecr.io/sovos-base-java:2024.06.17-21.0.3
USER root
VOLUME /tmp
ADD target/coupa-integration.jar app.jar
ENV JAVA_OPTS="-Xmx256m -Dlog4j2.formatMsgNoLookups=true"
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /app.jar --spring.config.name=application --spring.config.location=file:/sovos/config/" ]
