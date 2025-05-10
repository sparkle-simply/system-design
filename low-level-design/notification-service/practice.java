
// Core interfaces and abstract classes:

// Interface for notification channels
interface NotificationChannel {
    void send(Notification notification);
}

// Abstract class for notification
abstract class Notification {
    protected String id;
    protected String userId;
    protected String content;
    protected NotificationType type;
    protected NotificationPriority priority;
    protected LocalDateTime timestamp;

    // Constructor, getters, setters
}

// Interface for notification factory
interface NotificationFactory {
    Notification createNotification(String userId, String content, NotificationType type);
}

// Implementation of different notification channels:

class EmailNotification implements NotificationChannel {
    @Override
    public void send(Notification notification) {
        // Implementation for sending email
    }
}

class SMSNotification implements NotificationChannel {
    @Override
    public void send(Notification notification) {
        // Implementation for sending SMS
    }
}

class PushNotification implements NotificationChannel {
    @Override
    public void send(Notification notification) {
        // Implementation for sending push notification
    }
}

// Concrete notification types:

class MessageNotification extends Notification {
    private String messageId;
    private String senderName;

    // Constructor, getters, setters
}

class AlertNotification extends Notification {
    private String alertType;
    private boolean isUrgent;

    // Constructor, getters, setters
}

// User preference management:

class NotificationPreference {
    private String userId;
    private Map<NotificationType, Set<NotificationChannel>> channelPreferences;
    private Map<NotificationType, Boolean> optOutPreferences;

    public void addChannelPreference(NotificationType type, NotificationChannel channel) {
        // Implementation
    }

    public void optOut(NotificationType type) {
        // Implementation
    }

    public boolean isOptedOut(NotificationType type) {
        // Implementation
        return false;
    }
}

// Core notification service:

class NotificationService {
    private Map<String, User> users;
    private Map<String, NotificationPreference> preferences;
    private List<NotificationChannel> channels;
    private NotificationFactory notificationFactory;

    public void sendNotification(String userId, String content, NotificationType type) {
        NotificationPreference userPrefs = preferences.get(userId);
        if (userPrefs.isOptedOut(type)) {
            return;
        }

        Notification notification = notificationFactory.createNotification(userId, content, type);
        Set<NotificationChannel> userChannels = userPrefs.getChannelPreferences().get(type);

        for (NotificationChannel channel : userChannels) {
            channel.send(notification);
        }
    }

    public void registerUser(User user) {
        // Implementation
    }

    public void updatePreferences(String userId, NotificationPreference preference) {
        // Implementation
    }
}

// Supporting enums and classes:

enum NotificationType {
    MESSAGE,
    ALERT,
    FRIEND_REQUEST,
    SYSTEM_UPDATE
}

enum NotificationPriority {
    LOW,
    MEDIUM,
    HIGH,
    URGENT
}

class User {
    private String id;
    private String name;
    private String email;
    private String phone;

    // Constructor, getters, setters
}

// Builder pattern for complex notification creation:

class NotificationBuilder {
    private Notification notification;

    public NotificationBuilder withUserId(String userId) {
        // Implementation
        return this;
    }

    public NotificationBuilder withContent(String content) {
        // Implementation
        return this;
    }

    public NotificationBuilder withType(NotificationType type) {
        // Implementation
        return this;
    }

    public Notification build() {
        // Implementation
        return notification;
    }
}

/**
 *
 *
 *  +------------------------+         +-------------------------+
 * |    <<interface>>      |         |      <<abstract>>      |
 * |  NotificationChannel  |         |      Notification      |
 * +------------------------+         +-------------------------+
 * | +send(Notification)   |         | #id: String            |
 * |                      |         | #userId: String        |
 * +------------------------+         | #content: String       |
 *             ▲                     | #type: NotificationType|
 *             |                     | #priority: Priority     |
 *     +-----------------+          | #timestamp: DateTime    |
 *     |      |         |          +-------------------------+
 *     |      |         |                      ▲
 * +----------+--+ +---------+          +----------+----------+
 * |EmailChannel| |SMSChannel|          |          |          |
 * +-------------+ +----------+    +----------+ +----------+
 * |+send()      | |+send()   |    |Message   | |Alert    |
 * +-------------+ +----------+    |Notif.    | |Notif.    |
 *                                +----------+ +----------+
 *
 * +-------------------+         +----------------------+
 * |NotificationService|         |NotificationPreference|
 * +-------------------+         +----------------------+
 * |-users: Map        |         |-userId: String      |
 * |-preferences: Map  |    +--->|-channelPrefs: Map   |
 * |-channels: List    |         |-optOutPrefs: Map    |
 * +-------------------+         +----------------------+
 * |+sendNotification()|         |+addChannelPreference()|
 * |+registerUser()    |         |+optOut()             |
 * |+updatePreferences()|        |+isOptedOut()         |
 * +-------------------+         +----------------------+
 *
 * +-------------+              +------------------+
 * |    User     |              |<<enumeration>>   |
 * +-------------+              |NotificationType  |
 * |-id: String  |              +------------------+
 * |-name: String|              |MESSAGE           |
 * |-email: String|             |ALERT             |
 * |-phone: String|             |FRIEND_REQUEST    |
 * +-------------+              |SYSTEM_UPDATE     |
 *                             +------------------+
 *
 * +------------------+         +------------------+
 * |NotificationBuilder|        |<<enumeration>>   |
 * +------------------+        |NotificationPriority|
 * |-notification     |        +------------------+
 * +------------------+        |LOW               |
 * |+withUserId()     |        |MEDIUM            |
 * |+withContent()    |        |HIGH              |
 * |+withType()       |        |URGENT            |
 * |+build()          |        +------------------+
 * +------------------+
 *
 *
 * Key Design Patterns Used:
 *
 * Factory Pattern: For creating different types of notifications
 * Strategy Pattern: For different notification channels
 * Builder Pattern: For notification construction
 * Observer Pattern: For notification delivery
 * Singleton Pattern: For NotificationService
 * This design follows SOLID principles:
 *
 * Single Responsibility: Each class has a single responsibility
 * Open/Closed: New notification types and channels can be added without modifying existing code
 * Liskov Substitution: Notification subtypes can be used interchangeably
 * Interface Segregation: Interfaces are specific to their use cases
 * Dependency Inversion: High-level modules depend on abstractions
 * In an interview, you can discuss:
 *
 * How the system is extensible for new notification types
 * How it handles user preferences
 * How different channels are managed
 * How the system ensures loose coupling
 * How the system can be scaled
 * How error handling and retries can be implemented
 *
 */

