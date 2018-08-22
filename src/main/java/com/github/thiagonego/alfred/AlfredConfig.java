package br.com.twsoftware.alfred;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.Proxy.Type;

final public class AlfredConfig {
	private boolean usingProxy = false;
	private Proxy proxy;
	private static AlfredConfig instancia = new AlfredConfig();

	private AlfredConfig() {
	}

	public void setUsingProxy(boolean b) {
		this.usingProxy = b;
	}

	public boolean isUsingProxy() {
		return usingProxy;
	}

	public void setProxy(String host, int port) {
		SocketAddress sa = new InetSocketAddress(host, port);
		this.proxy = new Proxy(Type.HTTP, sa);
	}

	public void setProxy(Proxy proxy) {
		this.proxy = proxy;
	}

	public Proxy getProxy() {
		return this.proxy;
	}

	public static AlfredConfig getInstancia() {
		return instancia;
	}

	public static void setInstancia(AlfredConfig instancia) {
		AlfredConfig.instancia = instancia;
	}

}