import java.util.UUID;
import org.apache.jena.ext.com.google.common.base.CaseFormat;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.ResourceFactory;

public class Utils {

  static final public String INSTANCE_DATA_NS = "http://ai4bd.com/resource/data/";

  /**
   * Shortcut for Resource.getLocalName() to get the local name part of a Resource URI.
   *
   * @param resource The Resource from which to get the local name
   * @return The Resource URI without namespace or prefix (portion after last "/")
   */
  static public String localize(Resource resource) {
    return resource.getLocalName();
  }

  /**
   * Shortcut for Resource.getLocalName
   *
   * @param resourceUri The uri of resource
   * @return The resource name without complete uri
   */
  static public String localize(String resourceUri) {
    return localize(ResourceFactory.createResource(resourceUri));
  }

  /**
   * Generates a new instance URI (ID) for a Resource of the given type. The local name of the URI
   * is a randomly generated UUID.
   *
   * @param typeResource The Resource from which to use the local name as the type portion in the
   *                     generated URI. Must be a class type with CamelCase format in the local
   *                     name.
   * @return A generated data resource URI of the schema `[http://namespace/][type]/[UUID]`
   */
  static public String generateInstanceUri(Resource typeResource) {
    // transform the type name from CamelCase to kebab-case, will NOT work with lower camelCase (properties, etc.)
    String hyphenCaseType = CaseFormat.UPPER_CAMEL
        .to(CaseFormat.LOWER_HYPHEN, localize(typeResource));

    // concat the new URI to [http://namespace/][type]/[UUID]
    return INSTANCE_DATA_NS + hyphenCaseType + "/" + UUID.randomUUID();
  }
}
