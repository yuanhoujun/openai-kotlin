package com.aallam.openai.api.chat

import com.aallam.openai.api.model.ModelId
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Creates a completion for the chat message.
 */
@Serializable
public class ChatCompletionRequest(
    /**
     * ID of the model to use. Currently, only `gpt-3.5-turbo` and `gpt-3.5-turbo-0301` are supported.
     */
    @SerialName("model") public val model: ModelId,

    /**
     * The messages to generate chat completions for.
     */
    @SerialName("messages") public val messages: List<ChatMessage>,

    /**
     * What sampling temperature to use, between 0 and 2. Higher values like 0.8 will make the output more random,
     * while lower values like 0.2 will make it more focused and deterministic.
     *
     * We generally recommend altering this or [topP] but not both.
     */
    @SerialName("temperature") public val temperature: Double? = null,

    /**
     * An alternative to sampling with temperature, called nucleus sampling, where the model considers the results
     * of the tokens with top_p probability mass. So 0.1 means only the tokens comprising the top 10% probability mass
     * are considered.
     *
     * We generally recommend altering this or [temperature] but not both.
     */
    @SerialName("top_p") public val topP: Double? = null,

    /**
     * How many chat completion choices to generate for each input message.
     */
    @SerialName("n") public val n: Int? = null,

    /**
     * Up to 4 sequences where the API will stop generating further tokens.
     */
    @SerialName("stop") public val stop: List<String>? = null,

    /**
     * The maximum number of tokens allowed for the generated answer. By default, the number of tokens the model can
     * return will be (4096 - prompt tokens).
     */
    @SerialName("max_tokens") public val maxTokens: Int? = null,

    /**
     * Number between -2.0 and 2.0. Positive values penalize new tokens based on whether they appear in the text so far,
     * increasing the model's likelihood to talk about new topics.
     *
     * [Read more](https://platform.openai.com/docs/api-reference/parameter-details)
     */
    @SerialName("presence_penalty") public val presencePenalty: Double? = null,

    /**
     * Number between -2.0 and 2.0. Positive values penalize new tokens based on their existing frequency in the text so
     * far, decreasing the model's likelihood to repeat the same line verbatim.
     *
     * [Read more](https://platform.openai.com/docs/api-reference/parameter-details)
     */
    @SerialName("frequency_penalty") public val frequencyPenalty: Double? = null,

    /**
     * Modify the likelihood of specified tokens appearing in the completion.
     *
     * Accepts a json object that maps tokens (specified by their token ID in the tokenizer) to an associated bias value
     * from -100 to 100. Mathematically, the bias is added to the logits generated by the model prior to sampling.
     * The exact effect will vary per model, but values between -1 and 1 should decrease or increase likelihood of
     * selection; values like -100 or 100 should result in a ban or exclusive selection of the relevant token.
     */
    @SerialName("logit_bias") public val logitBias: Map<String, Int>? = null,
)

/**
 * The messages to generate chat completions for.
 */
public fun chatCompletionRequest(block: ChatCompletionRequestBuilder.() -> Unit): ChatCompletionRequest =
    ChatCompletionRequestBuilder().apply(block).build()

/**
 * Creates a completion for the chat message.
 */
@Serializable
public class ChatCompletionRequestBuilder {
    /**
     * ID of the model to use. Currently, only `gpt-3.5-turbo` and `gpt-3.5-turbo-0301` are supported.
     */
    public var model: ModelId? = null

    /**
     * The messages to generate chat completions for.
     */
    public var messages: List<ChatMessage>? = null

    /**
     * What sampling temperature to use, between 0 and 2. Higher values like 0.8 will make the output more random,
     * while lower values like 0.2 will make it more focused and deterministic.
     *
     * We generally recommend altering this or [topP] but not both.
     */
    public var temperature: Double? = null

    /**
     * An alternative to sampling with temperature, called nucleus sampling, where the model considers the results
     * of the tokens with top_p probability mass. So 0.1 means only the tokens comprising the top 10% probability mass
     * are considered.
     *
     * We generally recommend altering this or [temperature] but not both.
     */
    public var topP: Double? = null

    /**
     * How many chat completion choices to generate for each input message.
     */
    public var n: Int? = null

    /**
     * Up to 4 sequences where the API will stop generating further tokens.
     */
    public var stop: List<String>? = null

    /**
     * The maximum number of tokens allowed for the generated answer. By default, the number of tokens the model can
     * return will be (4096 - prompt tokens).
     */
    public var maxTokens: Int? = null

    /**
     * Number between -2.0 and 2.0. Positive values penalize new tokens based on whether they appear in the text so far,
     * increasing the model's likelihood to talk about new topics.
     *
     * [Read more](https://platform.openai.com/docs/api-reference/parameter-details)
     */
    public var presencePenalty: Double? = null

    /**
     * Number between -2.0 and 2.0. Positive values penalize new tokens based on their existing frequency in the text so
     * far, decreasing the model's likelihood to repeat the same line verbatim.
     *
     * [Read more](https://platform.openai.com/docs/api-reference/parameter-details)
     */
    public var frequencyPenalty: Double? = null

    /**
     * Modify the likelihood of specified tokens appearing in the completion.
     *
     * Accepts a json object that maps tokens (specified by their token ID in the tokenizer) to an associated bias value
     * from -100 to 100. Mathematically, the bias is added to the logits generated by the model prior to sampling.
     * The exact effect will vary per model, but values between -1 and 1 should decrease or increase likelihood of
     * selection; values like -100 or 100 should result in a ban or exclusive selection of the relevant token.
     */
    public var logitBias: Map<String, Int>? = null

    /**
     * The messages to generate chat completions for.
     */
    public fun messages(block: ChatMessagesBuilder.() -> Unit) {
        messages = ChatMessagesBuilder().apply(block).messages
    }

    /**
     * Builder of [ChatCompletionRequest] instances.
     */
    public fun build(): ChatCompletionRequest = ChatCompletionRequest(
        model = requireNotNull(model) { "model is required" },
        messages = requireNotNull(messages) { "messages is required" },
        temperature = temperature,
        topP = topP,
        n = n,
        stop = stop,
        maxTokens = maxTokens,
        presencePenalty = presencePenalty,
        frequencyPenalty = frequencyPenalty,
        logitBias = logitBias,
    )
}

/**
 * Creates a list of [ChatMessage].
 */
public class ChatMessagesBuilder {
    internal val messages = mutableListOf<ChatMessage>()

    /**
     * Creates a [ChatMessage] instance.
     */
    public fun message(block: ChatMessageBuilder.() -> Unit) {
        messages += ChatMessageBuilder().apply(block).build()
    }
}
