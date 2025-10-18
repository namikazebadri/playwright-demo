package hooks;

// Simple provider to allow step instances to access Hooks-managed page/context.
// In real projects use Dependency Injection (Cucumber PicoContainer) to inject Hooks instance.
public class HooksProvider {
    // These are set by the Hooks.before method
    public static Hooks currentHooks;

    public static com.microsoft.playwright.Page getPage() {
        if (currentHooks == null) {
            throw new IllegalStateException("Hooks not initialized. Make sure Hooks.before runs.");
        }
        return currentHooks.page;
    }
}
