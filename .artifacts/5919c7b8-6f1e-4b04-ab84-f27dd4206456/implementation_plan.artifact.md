# Implementation Plan - Refactor `ChatScreen` to match `ConversationsListScreen` style

The goal is to refactor `ChatScreen` to follow the structural pattern established in `ConversationsListScreen`, using a `Scaffold` with a `TopAppBar` and modern Material 3 components.

## Proposed Changes

### [feature:chat]

#### [MODIFY] [ChatScreen.kt](file:///D:/Prj/Android/Books/ThrivingAndroidDevelopment/WhatsPackt/feature/chat/src/main/java/com/packt/feature/chat/ui/ChatScreen.kt)
- Replace the simple `Surface` and `Column` with a `Scaffold`.
- Add a `TopAppBar`:
    - Title: "Chat: $chatId" (or just chatId if preferred).
    - Navigation Icon: Back arrow button that calls `onBack`.
    - Colors: Use `primaryContainer` for the background, matching `ConversationsListScreen`.
    - Actions: Add a placeholder menu button to match the visual style.
- Content:
    - Use `ChatList` to display messages.
    - Apply `paddingValues` from the `Scaffold`.
- Optional: Add a placeholder for message input (though not strictly requested, it's typical for a chat screen and would fill the "bottom bar" or bottom-of-screen area).

## Verification Plan

### Manual Verification
- Render the `ChatScreen` preview (if available or create one) to verify the UI structure.
- Check that the `TopAppBar` looks consistent with `ConversationsListScreen`.
- Ensure the back button is present and functional (if I can run it).
