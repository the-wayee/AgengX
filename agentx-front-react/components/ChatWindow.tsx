import { useEffect, useRef } from "react"
import type { Message } from "../types/chat"
import { MessageBubble } from "./MessageBubble"
import { MessageCircle } from "lucide-react"

interface ChatWindowProps {
  messages: Message[]
  isLoading: boolean
}

export function ChatWindow({ messages, isLoading }: ChatWindowProps) {
  const messagesEndRef = useRef<HTMLDivElement>(null)

  const scrollToBottom = () => {
    messagesEndRef.current?.scrollIntoView({ behavior: "smooth" })
  }

  // 当消息更新时滚动到底部
  useEffect(() => {
    scrollToBottom()
  }, [messages, isLoading]) // 当消息或加载状态改变时触发

  return (
    <div className="flex-1 overflow-y-auto p-6 space-y-6 bg-gradient-to-br from-blue-50 via-pink-50 to-green-50 dark:from-gray-900 dark:via-gray-800 dark:to-gray-900 animate__animated animate__fadeIn">
      {messages.map((message) => (
        <MessageBubble key={message.id} message={message} />
      ))}

      {/* Loading indicator */}
      {isLoading && (
        <div className="flex justify-start">
          <div className="bg-white/80 dark:bg-gray-700 rounded-lg p-3 max-w-xs lg:max-w-md shadow animate__animated animate__pulse">
            <div className="flex space-x-1">
              <div className="w-2 h-2 bg-blue-400 rounded-full animate-bounce"></div>
              <div className="w-2 h-2 bg-pink-400 rounded-full animate-bounce" style={{ animationDelay: "0.1s" }}></div>
              <div className="w-2 h-2 bg-green-400 rounded-full animate-bounce" style={{ animationDelay: "0.2s" }}></div>
            </div>
          </div>
        </div>
      )}

      {/* Empty state */}
      {messages.length === 0 && (
        <div className="flex items-center justify-center h-full text-gray-500 dark:text-gray-400 animate__animated animate__fadeIn">
          <div className="text-center">
            <MessageCircle className="w-16 h-16 mx-auto mb-4 opacity-50" />
            <p className="text-lg font-bold">Start a conversation</p>
            <p className="text-sm">Send a message to begin chatting with the AI assistant.</p>
          </div>
        </div>
      )}

      {/* 用于滚动的空白 div */}
      <div ref={messagesEndRef} />
    </div>
  )
}
