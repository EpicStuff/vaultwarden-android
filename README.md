# Bitwarden Android with http

https://github.com/bitwarden/android but with http allowed.

## Note to self
Build by running:
1. build sdk-internal: get commit has in `gradle/libs.versions.toml` (last commponent of bitwardenSdk), (`git clone https://github.com/bitwarden/sdk-internal`), checkout the hash, `cd sdk-internal/crates/bitwarden-uniffi/kotlin`,`rm -r sdk/src/main/java sdk/src/main/jniLibs/arm64-v8a/libbitwarden_uniffi.so`, `./publish-local.sh`
2. `set -x JAVA_HOME /usr/lib/jvm/java-21-openjdk && ./gradlew :app:assembleFdroidRelease`
3. ```sh
	/opt/android-sdk/build-tools/35.0.0/apksigner sign \
        --ks ... \
        --ks-type PKCS12 \
        --ks-pass pass:'...' \
        --key-pass pass:'...' \
        "$apk"