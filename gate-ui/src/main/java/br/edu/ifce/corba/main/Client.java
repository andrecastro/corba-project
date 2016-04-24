package br.edu.ifce.corba.main;


import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CORBA.UserException;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.of;

/**
 * Created by andrecoelho on 4/17/16.
 */
public class Client {
    private static final String NAME_SERVICE_REFERENCE = "NameService";
    private static final String NAME_COMPONENT_TYPE = "OBJ";

    private final NamingContext rootContext;

    public Client(ORB orb) throws InvalidName {
        rootContext = NamingContextHelper.narrow(orb.resolve_initial_references(NAME_SERVICE_REFERENCE));
    }

    public org.omg.CORBA.Object resolve(String[] path) throws UserException {
        List<NameComponent> pathList = of(path).map(p -> new NameComponent(p, NAME_COMPONENT_TYPE)).collect(toList());
        return rootContext.resolve(pathList.toArray(new NameComponent[pathList.size()]));
    }
}
