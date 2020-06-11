package web;

import infrastructure.Logger;

public abstract class BaseEntity {
    protected static Logger logger = Logger.getInstance();
    protected static Browser browser = Browser.getInstance();
}
