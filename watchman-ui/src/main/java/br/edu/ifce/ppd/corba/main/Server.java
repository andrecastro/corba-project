package br.edu.ifce.ppd.corba.main;

import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CORBA.UserException;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;
import org.omg.CosNaming.NamingContextPackage.AlreadyBound;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.omg.PortableServer.POAManagerPackage.AdapterInactive;
import org.omg.PortableServer.Servant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.of;

/**
 * Created by andrecoelho on 4/17/16.
 */
public class Server {
    private static final String ROOT_POA_REFERENCE = "RootPOA";
    private static final String NAME_SERVICE_REFERENCE = "NameService";
    private static final String NAME_COMPONENT_TYPE = "OBJ";

    private final ORB orb;
    private final POA rootPOA;
    private final NamingContext rootContext;
    private final Map<String, NamingContext> contexts;

    public Server(ORB orb) throws InvalidName {
        this.orb = orb;
        this.rootPOA = POAHelper.narrow(orb.resolve_initial_references(ROOT_POA_REFERENCE));
        this.rootContext = NamingContextHelper.narrow(orb.resolve_initial_references(NAME_SERVICE_REFERENCE));
        this.contexts = new HashMap<>();
    }

    public void register(Servant object, String[] pathName, String nameRef) throws UserException {
        org.omg.CORBA.Object objRef = rootPOA.servant_to_reference(object);
        NamingContext context = getOrBindNewContext(pathName[0], rootContext);

        for (int i = 1; i < pathName.length; i++) {
            context = getOrBindNewContext(pathName[i], context);
        }

        NameComponent[] path = { new NameComponent(nameRef, NAME_COMPONENT_TYPE) };
        context.rebind(path, objRef);
    }

    public void deregister(String[] path) throws UserException {
        List<NameComponent> pathList = of(path).map(p -> new NameComponent(p, NAME_COMPONENT_TYPE)).collect(toList());
        rootContext.unbind(pathList.toArray(new NameComponent[pathList.size()]));
        System.out.println("Guarda - Servidor fora ...");
    }


    public void start() throws AdapterInactive {
        rootPOA.the_POAManager().activate();
        System.out.println("Guarda - Servidor no Ar ...");
        orb.run();
    }

    private NamingContext getOrBindNewContext(String contextName, NamingContext parentContext) throws UserException {
        NamingContext context = contexts.get(contextName);

        if (context == null) {
            context = bindContext(contextName, parentContext);
            contexts.put(contextName, context);
        }

        return context;
    }

    /**
     * Bind a new context or, if it is already bound, create a new one and rebind with the same name of the old
     * @param contextName Name of the context
     * @param parentContext The parent of the contex
     * @return The context bound
     * @throws UserException
     */
    private NamingContext bindContext(String contextName, NamingContext parentContext) throws UserException {
        NamingContext context;
        NameComponent[] path = { new NameComponent(contextName, NAME_COMPONENT_TYPE) };

        try {
            context = parentContext.bind_new_context(path);
        } catch (AlreadyBound e) {
            context = parentContext.new_context();
            parentContext.rebind_context(path, context);
        }

        return context;
    }
}
