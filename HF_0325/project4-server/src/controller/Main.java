package controller;


import model.RemoteServices;


public class Main {
  public static void main(String[] args) {
		RemoteServices remote = new RemoteServices();
		remote.StartServer();
  }
}