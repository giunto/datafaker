package net.datafaker;

/**
 * @since 0.8.0
 */
public class Name extends AbstractProvider {

    /**
     * Internal constructor, not to be used by clients.  Instances of {@link Name} should be accessed via
     * {@link Faker#name()}.
     */
    protected Name(Faker faker) {
        super(faker);
    }

    /**
     * A multipart name composed of an optional prefix, a firstname and a lastname
     * or other possible variances based on locale.  Examples:
     * <ul>
     *     <li>James Jones Jr.</li>
     *     <li>Julie Johnson</li>
     * </ul>
     *
     * @return a random name with given and family names and an optional suffix.
     */
    public String name() {
        return faker.fakeValuesService().resolve("name.name", this);
    }

    /**
     * A multipart name composed of an optional prefix, a given and family name,
     * another 'firstname' for the middle name and an optional suffix such as Jr.
     * Examples:
     * <ul>
     *     <li>Mrs. Ella Geraldine Fitzgerald</li>
     *     <li>Jason Tom Sawyer Jr.</li>
     *     <li>Helen Jessica Troy</li>
     * </ul>
     *
     * @return a random name with a middle name component with optional prefix and suffix
     */
    public String nameWithMiddle() {
        return faker.fakeValuesService().resolve("name.name_with_middle", this);
    }

    /**
     * Returns the same value as {@link #name()}
     *
     * @see Name#name()
     */
    public String fullName() {
        return name();
    }

    /**
     * Returns a random 'given' name such as Aaliyah, Aaron, Abagail or Abbey
     *
     * @return a 'given' name such as Aaliyah, Aaron, Abagail or Abbey
     */
    public String firstName() {
        return faker.fakeValuesService().resolve("name.first_name", this);
    }

    /**
     * Returns a random last name such as Smith, Jones or Baldwin
     *
     * @return a random last name such as Smith, Jones or Baldwin
     */
    public String lastName() {
        return faker.fakeValuesService().resolve("name.last_name", this);
    }

    /**
     * Returns a name prefix such as Mr., Mrs., Ms., Miss, or Dr.
     *
     * @return a name prefix such as Mr., Mrs., Ms., Miss, or Dr.
     */
    public String prefix() {
        return faker.fakeValuesService().resolve("name.prefix", this);
    }

    /**
     * Returns a name suffix such as Jr., Sr., I, II, III, IV, V, MD, DDS, PhD or DVM
     *
     * @return a name suffix such as Jr., Sr., I, II, III, IV, V, MD, DDS, PhD or DVM
     */
    public String suffix() {
        return faker.fakeValuesService().resolve("name.suffix", this);
    }

    /**
     * A three part title composed of a descriptor level and job.  Some examples are :
     * <ul>
     *   <li>(template) {descriptor} {level} {job}</li>
     *   <li>Lead Solutions Specialist</li>
     *   <li>National Marketing Manager</li>
     *   <li>Central Response Liaison</li>
     * </ul>
     *
     * @return a random three part job title
     */
    public String title() {
        return String.join(" ",
            faker.fakeValuesService().resolve("name.title.descriptor", this),
            faker.fakeValuesService().resolve("name.title.level", this),
            faker.fakeValuesService().resolve("name.title.job", this)
        );
    }

    /**
     * A lowercase username composed of the first_name and last_name joined with a '.'. Some examples are:
     * <ul>
     *     <li>(template) {@link #firstName()}.{@link #lastName()}</li>
     *     <li>jim.jones</li>
     *     <li>jason.leigh</li>
     *     <li>tracy.jordan</li>
     * </ul>
     *
     * @return a random two part user name.
     * @see Name#firstName()
     * @see Name#lastName()
     */
    public String username() {

        StringBuilder result = new StringBuilder();
        final String firstName = firstName().toLowerCase(faker.getLocale()) + "." + lastName().toLowerCase(faker.getLocale());
        for (int i = 0; i < firstName.length(); i++) {
            final char c = firstName.charAt(i);
            if (c == '\'' || Character.isWhitespace(c)) {
                continue;
            }
            result.append(c);
        }
        return result.toString();
    }
}
