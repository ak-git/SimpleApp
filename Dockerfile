FROM openjdk:22

# Create a custom user with UID 1234 and GID 1234
# https://www.docker.com/blog/understanding-the-docker-user-instruction/
RUN groupadd -g 1234 customgroup && \
    useradd -m -u 1234 -g customgroup customuser

# Switch to the custom user
USER customuser

# Copy the app files from host machine to image filesystem
COPY hello/build/classes/java/main /home/customuser

# Set the directory for executing future commands
WORKDIR /home/customuser

# Run the Main class
# https://docs.docker.com/reference/build-checks/json-args-recommended/
CMD ["java", "com.ak.app.MainApp"]