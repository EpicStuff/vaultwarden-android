package com.x8bit.bitwarden.data.platform

import android.security.NetworkSecurityPolicy
import com.bitwarden.ui.platform.base.BaseRobolectricTest
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Asserts the app's `network_security_config.xml` policy: cleartext HTTP is permitted to
 * arbitrary self-hosted hostnames (so users can run Vaultwarden over plain HTTP on their LAN
 * or VPN), while Bitwarden's own production domains continue to require HTTPS.
 */
class NetworkSecurityConfigTest : BaseRobolectricTest() {

    private val policy: NetworkSecurityPolicy = NetworkSecurityPolicy.getInstance()

    @Test
    fun `cleartext HTTP is permitted for self-hosted hostnames`() {
        assertTrue(policy.isCleartextTrafficPermitted("vaultwarden.lan"))
        assertTrue(policy.isCleartextTrafficPermitted("vault.example.com"))
        assertTrue(policy.isCleartextTrafficPermitted("192.168.1.10"))
        assertTrue(policy.isCleartextTrafficPermitted("10.0.0.5"))
        assertTrue(policy.isCleartextTrafficPermitted("localhost"))
    }

    @Test
    fun `cleartext HTTP is not permitted for bitwarden domains`() {
        assertFalse(policy.isCleartextTrafficPermitted("bitwarden.com"))
        assertFalse(policy.isCleartextTrafficPermitted("vault.bitwarden.com"))
        assertFalse(policy.isCleartextTrafficPermitted("api.bitwarden.com"))
        assertFalse(policy.isCleartextTrafficPermitted("bitwarden.eu"))
        assertFalse(policy.isCleartextTrafficPermitted("api.bitwarden.eu"))
        assertFalse(policy.isCleartextTrafficPermitted("bitwarden.pw"))
    }
}
